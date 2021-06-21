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
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = READ_WRITE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment implements IdentifiableEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

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

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;

    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id")
    @LazyToOne(PROXY)
    @LazyGroup("user")
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    @ToString.Exclude
    private User user;


    public Comment text(String text) {
        this.text = text;
        return this;
    }

    public Comment product(Product product) {
        this.product = product;
        return this;
    }

    public Comment user(User user) {
        this.user = user;
        return this;
    }
}