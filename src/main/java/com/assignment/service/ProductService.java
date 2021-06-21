package com.assignment.service;

import com.assignment.DTO.entity.create.CreateProductDTO;
import com.assignment.DTO.entity.read.ProductDTO;
import com.assignment.DTO.entity.update.UpdateProductDTO;
import com.assignment.mapper.ProductMapper;
import com.assignment.model.Product;
import com.assignment.repository.ProductRepository;
import com.assignment.service.query.ProductCriteria;
import com.assignment.service.query.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@Slf4j
public class ProductService extends BaseService<Product, ProductRepository,
        CreateProductDTO, UpdateProductDTO, ProductDTO, ProductMapper> {

    public ProductService(ProductRepository repository, ProductMapper mapper, SecurityService securityService) {
        super(repository, mapper, securityService);
    }

    @Override
    protected void preCreate(Product entity) {
        entity.setAvgRate(0D);
    }
    @Override
    protected void preUpdate(Product entity) {
        Product oldProduct = repository.getOne(entity.getId());
        entity.setAvgRate(oldProduct.getAvgRate());
    }

    public List<ProductDTO> getAllProducts(ProductCriteria searchCriteria) {
        ProductSpecification spec = new ProductSpecification(searchCriteria);
        List<ProductDTO> result = mapper.toDto(repository.findAll(Specification.where(spec)));
        return result;
    }
}