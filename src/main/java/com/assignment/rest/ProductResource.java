package com.assignment.rest;

import com.assignment.DTO.entity.create.CreateProductDTO;
import com.assignment.DTO.entity.read.ProductDTO;
import com.assignment.DTO.entity.update.UpdateProductDTO;
import com.assignment.mapper.ProductMapper;
import com.assignment.model.Product;
import com.assignment.repository.ProductRepository;
import com.assignment.service.ProductService;
import com.assignment.service.query.ProductCriteria;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * REST controller for managing rate.
 */
@RestController
@RequestMapping("/api/products")
@Api(tags = "products")
@Slf4j
public class ProductResource extends BaseResource<Product, ProductRepository, CreateProductDTO, UpdateProductDTO, ProductDTO, ProductMapper, ProductService> {

    public ProductResource(ProductService service) {
        super(service);
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        return createEntity(createProductDTO);
    }

    @PutMapping("")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody UpdateProductDTO updateProductDTO) {
        return updateEntity(updateProductDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return getEntity(id);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts(ProductCriteria searchCriteria) {
        List<ProductDTO> dtoList = service.getAllProducts(searchCriteria);
        return ResponseEntity.ok().body(dtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return deleteEntity(id);
    }
}