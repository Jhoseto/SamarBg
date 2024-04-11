package org.samarBg.models;

import org.samarBg.models.enums.UserRole;

import javax.persistence.*;

/**
 * Represents an entity for user roles in the application.
 */
@Entity
@Table(name = "user_roles")
public class UserRoleEntity {

    /**
     * The unique identifier of the user role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The role associated with the user (enum type).
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * Retrieves the unique identifier of the user role.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user role.
     */
    public UserRoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Retrieves the role associated with the user.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role associated with the user.
     */
    public UserRoleEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }

    /**
     * Returns a string representation of the UserRoleEntity object.
     */
    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
