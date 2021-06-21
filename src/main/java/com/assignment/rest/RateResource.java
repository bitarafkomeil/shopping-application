package com.assignment.rest;

import com.assignment.DTO.entity.create.CreateRateDTO;
import com.assignment.DTO.entity.read.RateDTO;
import com.assignment.DTO.entity.update.UpdateRateDTO;
import com.assignment.mapper.RateMapper;
import com.assignment.model.Rate;
import com.assignment.repository.RateRepository;
import com.assignment.service.RateService;
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
@RequestMapping("/api/rates")
@Api(tags = "rates")
@Slf4j
public class RateResource extends BaseResource<Rate, RateRepository, CreateRateDTO, UpdateRateDTO, RateDTO, RateMapper, RateService> {

    public RateResource(RateService service) {
        super(service);
    }

    @PostMapping("")
    public ResponseEntity<RateDTO> createRate(@Valid @RequestBody CreateRateDTO createRateDTO) {
        return createEntity(createRateDTO);
    }

    @PutMapping("")
    public ResponseEntity<RateDTO> updateRate(@Valid @RequestBody UpdateRateDTO updateRateDTO) {
        return updateEntity(updateRateDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateDTO> getRate(@PathVariable Long id) {
        return getEntity(id);
    }

    @GetMapping("")
    public ResponseEntity<List<RateDTO>> getAllRates() {
        return getAllEntities();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        return deleteEntity(id);
    }
}