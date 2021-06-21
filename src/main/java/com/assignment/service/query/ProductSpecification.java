package com.assignment.service.query;

import com.assignment.model.Product;
import com.assignment.model.Product_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private ProductCriteria criteria;

    public ProductSpecification(ProductCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();


        if (criteria.getPriceGraterThan() != null) {
            predicates.add(builder.greaterThan(root.get(Product_.price), criteria.getPriceGraterThan()));
        }
        if (criteria.getPriceLessThan() != null) {
            predicates.add(builder.lessThan(root.get(Product_.price), criteria.getPriceGraterThan()));
        }
        if (criteria.getName() != null) {
            predicates.add(builder.like(root.get(Product_.name), criteria.getName()));
        }
        if (criteria.getRateGraterThan() != null) {
            predicates.add(builder.greaterThan(root.get(Product_.avgRate), criteria.getRateGraterThan().doubleValue()));
        }
        if (criteria.getRateLessThan() != null) {
            predicates.add(builder.lessThan(root.get(Product_.avgRate), criteria.getRateLessThan().doubleValue()));
        }
        if (predicates.size()>0){
            Predicate finalPredicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));
            query.where(builder.and(finalPredicate));
        }
        return query.getRestriction();
    }
}