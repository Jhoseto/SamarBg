package org.samarBg.model.entities;

import org.samarBg.model.entities.enums.UserRoleEnum;
import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


    public long getId() {
        return id;
    }

    public UserRoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getRole(String role) {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
