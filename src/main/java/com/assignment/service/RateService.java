package com.assignment.service;

import com.assignment.DTO.entity.create.CreateRateDTO;
import com.assignment.DTO.entity.read.RateDTO;
import com.assignment.DTO.entity.update.UpdateRateDTO;
import com.assignment.exception.BadRequestException;
import com.assignment.exception.ErrorConstants;
import com.assignment.mapper.RateMapper;
import com.assignment.model.Product;
import com.assignment.model.Rate;
import com.assignment.model.User;
import com.assignment.repository.ProductRepository;
import com.assignment.repository.RateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@Slf4j
public class RateService extends BaseService<Rate, RateRepository,
        CreateRateDTO, UpdateRateDTO, RateDTO, RateMapper> {

    private final ProductRepository productRepository;

    public RateService(RateRepository repository, RateMapper mapper, SecurityService securityService, ProductRepository productRepository) {
        super(repository, mapper, securityService);
        this.productRepository = productRepository;
    }

    @Override
    protected void preCreate(Rate entity) {
        User user = securityService.getCurrentUser();
        entity.setUser(user);
        Optional<Rate> oldRate = repository.findByProductIdAndUserId(entity.getProduct().getId(), entity.getUser().getId());
        if (oldRate.isPresent())
            entity.setId(oldRate.get().getId());
        if (entity.getRate() > 5 || entity.getRate() < 1)
            throw new BadRequestException(ErrorConstants.VALIDATION_TYPE, "Rate Number Incorrect");
    }

    @Override
    protected void postCreate(Rate entity) {
        updateProductRate(entity);
    }


    @Override
    protected void preUpdate(Rate entity) {
        Rate oldRate = repository.findById(entity.getId()).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "RateId Incorrect"));
        checkEntityUser(oldRate);
        User user = securityService.getCurrentUser();
        entity.setUser(user);
    }

    @Override
    protected void postUpdate(Rate entity) {
        updateProductRate(entity);
    }

    private void updateProductRate(Rate entity){
        List<Rate> rateList = repository.findAllByProductId(entity.getProduct().getId());
        Double avg = rateList.stream().mapToDouble(Rate::getRate).sum()/rateList.size();
        Product product = productRepository.getOne(entity.getProduct().getId());
        product.setAvgRate(avg);
        productRepository.save(product);
    }

    @Override
    protected void preDelete(Long id) {
        Rate rate = repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "RateId Incorrect"));
        checkEntityUser(rate);
    }

    private void checkEntityUser(Rate rate) {
        if (rate.getUser().getId() != securityService.getCurrentUser().getId())
            throw new AccessDeniedException("Access denied");
    }
}