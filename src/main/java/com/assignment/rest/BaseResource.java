package com.assignment.rest;


import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.exception.BadRequestException;
import com.assignment.exception.ErrorConstants;
import com.assignment.mapper.BaseMapper;
import com.assignment.model.IdentifiableEntity;
import com.assignment.repository.BaseRepository;
import com.assignment.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RequiredArgsConstructor
public abstract class BaseResource<E extends IdentifiableEntity, R extends BaseRepository<E>, CD, UD extends IdentifiableDTO, D extends IdentifiableDTO, M extends BaseMapper<CD, UD, D, E>, S extends BaseService<E, R, CD, UD, D, M>> {

    protected final S service;

    public ResponseEntity<D> createEntity(CD createDto) {
        D dto = service.create(createDto);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<D> updateEntity(UD updateDto) {
        D dto = service.update(updateDto);
        return ResponseEntity.ok()
                .body(dto);
    }

    public ResponseEntity<D> getEntity(Long id) {
        D entityDto = service.findOne(id).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "Entity Id Incorrect"));
        return ResponseEntity.ok()
                .body(entityDto);
    }

    public ResponseEntity<List<D>> getAllEntities() {
        List<D> entities = service.findAll();
        return ResponseEntity.ok()
                .body(entities);
    }

    public ResponseEntity<Void> deleteEntity(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}