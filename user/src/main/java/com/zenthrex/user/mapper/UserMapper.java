package com.zenthrex.user.mapper;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.RoleEnum;
import com.zenthrex.core.enums.UserStatus;
import com.zenthrex.user.dto.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    // Basic mappings
    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "createdOn", target = "createdAt")
    @Mapping(source = "updatedOn", target = "updatedAt")
    UserDto toDto(User user);

    @Mapping(source = "firstName", target = "firstname")
    @Mapping(source = "lastName", target = "lastname")
    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> users);

    // Detailed user mapping
    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "createdOn", target = "createdAt")
    @Mapping(source = "updatedOn", target = "updatedAt")
    @Mapping(source = "lastLoginAt", target = "lastLoginAt")
    @Mapping(source = "verifiedAt", target = "verifiedAt")
    @Mapping(source = "verifiedBy", target = "verifiedBy")
    UserDetailDto toDetailDto(User user);

    // Profile mapping
    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "createdOn", target = "createdAt")
    @Mapping(target = "twoFactorEnabled", constant = "false") // Will implement later
    UserProfileDto toProfileDto(User user);

    // Client detail mapping
    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "role", target = "clientType")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdOn", target = "createdAt")
    @Mapping(source = "lastLoginAt", target = "lastActivityAt")
    @Mapping(target = "totalBookings", constant = "0") // Will be calculated later
    @Mapping(target = "totalOrders", constant = "0") // Will be calculated later
    @Mapping(target = "totalSpent", constant = "0.00") // Will be calculated later
    @Mapping(target = "notesCount", constant = "0") // Will be calculated later
    ClientDetailDto toClientDetailDto(User user);

    // Update entity from DTO
    @Mapping(source = "firstName", target = "firstname")
    @Mapping(source = "lastName", target = "lastname")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "tokens", ignore = true)
    void updateEntityFromDto(UpdateProfileRequest dto, @MappingTarget User entity);

    // Default methods
    default String mapStatusToString(UserStatus status) {
        return status != null ? status.name() : null;
    }

    default String mapRoleToString(RoleEnum role) {
        return role != null ? role.name() : null;
    }
}