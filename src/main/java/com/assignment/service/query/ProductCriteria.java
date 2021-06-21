package com.assignment.service.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCriteria {

    private Long priceGraterThan;
    private Long priceLessThan;
    private Short rateLessThan;
    private Short rateGraterThan;
    private String name;
}