package com.zenthrex.core.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;

import static com.zenthrex.core.enums.Permission.*;


@Getter
public enum RoleEnum {
    BUYER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    USER_CREATE
            )
    ),
    SELLER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    USER_CREATE
            )
    );
    private final Set<Permission> permissions;

    RoleEnum(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
