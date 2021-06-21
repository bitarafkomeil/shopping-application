package com.assignment.model;

import com.assignment.model.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

/**
 * A User.
 */
@Entity
@Table(name = "user")
@Cache(usage = READ_WRITE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements IdentifiableEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "block", nullable = false)
    private Boolean block;

    @NotNull
    @Enumerated(STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;


    public User userName(String userName) {
        this.userName = userName;
        return this;
    }

    public User block(Boolean block) {
        this.block = block;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }
}