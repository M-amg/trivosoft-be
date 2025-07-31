// accessory/src/main/java/com/zenthrex/accessory/services/AccessoryService.java
package com.zenthrex.accessory.services;

import com.zenthrex.accessory.mappers.AccessoryMapper;
import com.zenthrex.core.dtos.AccessoryDto;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.enums.AccessoryStatus;
import com.zenthrex.core.exception.ResourceNotFoundException;
import com.zenthrex.core.repositories.AccessoryRepository;
import com.zenthrex.core.repositories.UserRepository;
import com.zenthrex.notification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final UserRepository userRepository;
    private final AccessoryMapper accessoryMapper;
    private final AccessoryValidationService validationService;
    private final NotificationService notificationService;

    /**
     * Create a new accessory listing
     */
    public AccessoryDto createAccessory(AccessoryDto accessoryDto) {
        log.info("Creating new accessory: {}", accessoryDto.getTitle());

        // Get current user
        User currentUser = getCurrentUser();

        // Validate accessory data
        validationService.validateNewAccessory(accessoryDto);

        // Map DTO to entity
        Accessory accessory = accessoryMapper.toEntity(accessoryDto);
        accessory.setSeller(currentUser);
        accessory.setStatus(AccessoryStatus.PENDING_APPROVAL);

        // Generate SKU if not provided
        if (accessory.getSku() == null || accessory.getSku().isEmpty()) {
            accessory.setSku(generateSKU(accessory));
        }

        Accessory savedAccessory = accessoryRepository.save(accessory);

        // Send notification to admins for approval
        notificationService.sendAccessorySubmissionNotification(savedAccessory);

        log.info("Accessory created successfully with ID: {}", savedAccessory.getId());
        return accessoryMapper.toDto(savedAccessory);
    }

    /**
     * Find accessory by ID
     */
    @Transactional(readOnly = true)
    public AccessoryDto findById(Long id) {
        log.info("Fetching accessory with ID: {}", id);

        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with ID: " + id));

        return accessoryMapper.toDto(accessory);
    }

    /**
     * Find all accessories with filters
     */
    @Transactional(readOnly = true)
    public Page<AccessoryDto> findAllWithFilters(String category, String status, String search,
                                                 BigDecimal minPrice, BigDecimal maxPrice,
                                                 String brand, Boolean inStock, Pageable pageable) {

        log.info("Fetching accessories with filters - category: {}, status: {}, search: {}",
                category, status, search);

        Page<Accessory> accessories = accessoryRepository.findWithFilters(
                category, status, search, minPrice, maxPrice, brand, inStock, pageable);

        return accessories.map(accessoryMapper::toDto);
    }

    /**
     * Update accessory
     */
    public AccessoryDto updateAccessory(Long id, AccessoryDto accessoryDto) {
        log.info("Updating accessory with ID: {}", id);

        Accessory existingAccessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        // Validate ownership or admin rights
        validateAccessoryOwnership(existingAccessory);

        // Validate update data
        validationService.validateAccessoryUpdate(accessoryDto, existingAccessory);

        // Update fields
        updateAccessoryFields(existingAccessory, accessoryDto);

        Accessory updatedAccessory = accessoryRepository.save(existingAccessory);

        log.info("Accessory updated successfully: {}", updatedAccessory.getId());
        return accessoryMapper.toDto(updatedAccessory);
    }

    /**
     * Delete accessory
     */
    public void deleteAccessory(Long id) {
        log.info("Deleting accessory with ID: {}", id);

        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        validateAccessoryOwnership(accessory);

        // Check if accessory has pending orders
        if (hasActivOrders(accessory)) {
            throw new IllegalStateException("Cannot delete accessory with active orders");
        }

        accessoryRepository.save(accessory);

        log.info("Stock updated successfully for accessory: {}", id);
    }

    /**
     * Approve accessory (Admin/Agent only)
     */
    public void approveAccessory(Long id, String notes) {
        log.info("Approving accessory with ID: {}", id);

        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        if (accessory.getStatus() != AccessoryStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Accessory is not pending approval");
        }

        accessory.setStatus(AccessoryStatus.AVAILABLE);
        accessoryRepository.save(accessory);

        // Send approval notification to seller
        notificationService.sendAccessoryApprovalNotification(accessory, notes);

        log.info("Accessory approved successfully: {}", id);
    }

    /**
     * Reject accessory (Admin/Agent only)
     */
    public void rejectAccessory(Long id, String reason) {
        log.info("Rejecting accessory with ID: {}", id);

        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        if (accessory.getStatus() != AccessoryStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Accessory is not pending approval");
        }

        accessory.setStatus(AccessoryStatus.REJECTED);
        accessoryRepository.save(accessory);

        // Send rejection notification to seller
        notificationService.sendAccessoryRejectionNotification(accessory, reason);

        log.info("Accessory rejected successfully: {}", id);
    }

    /**
     * Get accessories for current seller
     */
    @Transactional(readOnly = true)
    public Page<AccessoryDto> getMyAccessories(String status, Pageable pageable) {
        User currentUser = getCurrentUser();
        log.info("Fetching accessories for seller: {}", currentUser.getEmail());

        Page<Accessory> accessories = accessoryRepository.findBySellerAndStatus(
                currentUser, status, pageable);

        return accessories.map(accessoryMapper::toDto);
    }

    /**
     * Get pending accessories for approval (Admin/Agent only)
     */
    @Transactional(readOnly = true)
    public Page<AccessoryDto> getPendingAccessories(Pageable pageable) {
        log.info("Fetching pending accessories for approval");

        Page<Accessory> accessories = accessoryRepository.findByStatus(
                AccessoryStatus.PENDING_APPROVAL, pageable);

        return accessories.map(accessoryMapper::toDto);
    }

    /**
     * Reduce stock when order is placed
     */
    public void reduceStock(Long accessoryId, Integer quantity) {
        log.info("Reducing stock for accessory {} by quantity: {}", accessoryId, quantity);

        Accessory accessory = accessoryRepository.findById(accessoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        if (accessory.getStockQuantity() < quantity) {
            throw new IllegalStateException("Insufficient stock available");
        }

        accessory.setStockQuantity(accessory.getStockQuantity() - quantity);

        if (accessory.getStockQuantity() == 0) {
            accessory.setStatus(AccessoryStatus.OUT_OF_STOCK);
        }

        accessoryRepository.save(accessory);

        log.info("Stock reduced successfully for accessory: {}", accessoryId);
    }

    /**
     * Restore stock when order is cancelled
     */
    public void restoreStock(Long accessoryId, Integer quantity) {
        log.info("Restoring stock for accessory {} by quantity: {}", accessoryId, quantity);

        Accessory accessory = accessoryRepository.findById(accessoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        accessory.setStockQuantity(accessory.getStockQuantity() + quantity);

        if (accessory.getStatus() == AccessoryStatus.OUT_OF_STOCK && accessory.getStockQuantity() > 0) {
            accessory.setStatus(AccessoryStatus.AVAILABLE);
        }

        accessoryRepository.save(accessory);

        log.info("Stock restored successfully for accessory: {}", accessoryId);
    }

    // ==================== PRIVATE HELPER METHODS ====================

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
    }

    private void validateAccessoryOwnership(Accessory accessory) {
        User currentUser = getCurrentUser();

        // Allow if user is admin/agent or owns the accessory
        if (!currentUser.getRole().name().equals("ADMIN") &&
                !currentUser.getRole().name().equals("AGENT") &&
                !accessory.getSeller().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("You don't have permission to modify this accessory");
        }
    }

    private String generateSKU(Accessory accessory) {
        String prefix = accessory.getCategory().name().substring(0, 3).toUpperCase();
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(8);
        return prefix + "-" + timestamp;
    }

    private void updateAccessoryFields(Accessory existing, AccessoryDto dto) {
        if (dto.getTitle() != null) existing.setTitle(dto.getTitle());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        if (dto.getPrice() != null) existing.setPrice(dto.getPrice());
        if (dto.getDeliveryPrice() != null) existing.setDeliveryPrice(dto.getDeliveryPrice());
        if (dto.getBrand() != null) existing.setBrand(dto.getBrand());
        if (dto.getModel() != null) existing.setModel(dto.getModel());
        if (dto.getWeight() != null) existing.setWeight(dto.getWeight());
        if (dto.getDimensions() != null) existing.setDimensions(dto.getDimensions());
        if (dto.getColor() != null) existing.setColor(dto.getColor());
        if (dto.getMaterial() != null) existing.setMaterial(dto.getMaterial());
        if (dto.getLatitude() != null) existing.setLatitude(dto.getLatitude());
        if (dto.getLongitude() != null) existing.setLongitude(dto.getLongitude());

        existing.setUpdatedOn(LocalDateTime.now());
    }

    private boolean hasActivOrders(Accessory accessory) {
        // Implementation would check if accessory has any pending/active orders
        // This is a placeholder - implement based on your order system
        return false;
    }
}
