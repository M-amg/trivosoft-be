package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.enums.AccessoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    boolean existsBySku(String sku);

    Page<Accessory> findByStatus(AccessoryStatus status, Pageable pageable);

    Page<Accessory> findBySellerAndStatus(User seller, String status, Pageable pageable);

    @Query("""
            SELECT a FROM Accessory a
            WHERE (:category IS NULL OR a.category = :category)
            AND (:status IS NULL OR a.status = :status)
            AND (:search IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :search, '%')) 
                 OR LOWER(a.description) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:minPrice IS NULL OR a.price >= :minPrice)
            AND (:maxPrice IS NULL OR a.price <= :maxPrice)
            AND (:brand IS NULL OR LOWER(a.brand) = LOWER(:brand))
            AND (:inStock IS NULL OR (:inStock = true AND a.stockQuantity > 0) 
                 OR (:inStock = false))
            """)
    Page<Accessory> findWithFilters(
            @Param("category") String category,
            @Param("status") String status,
            @Param("search") String search,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("brand") String brand,
            @Param("inStock") Boolean inStock,
            Pageable pageable
    );

    @Query("""
            SELECT a FROM Accessory a
            WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(a.description) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(a.brand) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(a.model) LIKE LOWER(CONCAT('%', :query, '%'))
            """)
    Page<Accessory> searchByQuery(@Param("query") String query, Pageable pageable);
}
