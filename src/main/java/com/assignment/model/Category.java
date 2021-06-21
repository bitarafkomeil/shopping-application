package com.assignment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = READ_WRITE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category implements IdentifiableEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


    public Category name(String name) {
        this.name = name;
        return this;
    }
}