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
 * A Rate.
 */
@Entity
@Table(name = "rate")
@Cache(usage = READ_WRITE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rate implements IdentifiableEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "text", nullable = false)
    private Short rate;

    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Long productId;

    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "product_id")
    @LazyToOne(PROXY)
    @LazyGroup("product")
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    @ToString.Exclude
    private Product product;

    @Column(name = "user_id", unique = true, insertable = false, updatable = false)
    private Long userId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @LazyToOne(PROXY)
    @LazyGroup("user")
    @ToString.Exclude
    private User user;


    public Rate rate(Short rate) {
        this.rate = rate;
        return this;
    }

    public Rate product(Product product) {
        this.product = product;
        return this;
    }

    public Rate user(User user) {
        this.user = user;
        return this;
    }
}