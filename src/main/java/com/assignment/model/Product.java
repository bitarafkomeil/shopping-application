package com.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;
import static org.hibernate.annotations.LazyToOneOption.PROXY;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = READ_WRITE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements IdentifiableEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Long categoryId;

    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "category_id")
    @LazyToOne(PROXY)
    @LazyGroup("category")
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    @ToString.Exclude
    private Category category;

    @Column(name = "avg_rate", nullable = false)
    private Double avgRate;


    public Product name(String name) {
        this.name = name;
        return this;
    }

    public Product price(Long price) {
        this.price = price;
        return this;
    }

    public Product avgRate(Double avgRate) {
        this.avgRate = avgRate;
        return this;
    }

    public Product category(Category category) {
        this.category = category;
        return this;
    }
}