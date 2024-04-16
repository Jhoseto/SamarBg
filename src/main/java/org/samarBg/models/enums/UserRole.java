package org.samarBg.models.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Represents the roles available for users in the system.
 */
public enum UserRole {
    /**
     * Administrator role.
     */
    ADMIN,

    /**
     * Regular user role.
     */
    USER;

}
