package com.assignment.service;

import com.assignment.mapper.BaseMapper;
import com.assignment.model.IdentifiableEntity;
import com.assignment.repository.BaseRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseService<E extends IdentifiableEntity, R extends BaseRepository<E>, CD, UD, D, M extends BaseMapper<CD, UD, D, E>> {

    protected final R repository;

    protected final M mapper;

    protected final SecurityService securityService;

    @Transactional
    public D create(CD createDto) {
        E entity = mapper.fromCreateDTO(createDto);
        entity = createEntity(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public E createEntity(E entity) {
        preCreate(entity);
        entity = repository.save(entity);
        postCreate(entity);
        return entity;
    }

    protected void preCreate(E entity) {
    }
    protected void postCreate(E entity) {
    }

    @Transactional
    public D update(UD updateDto) {
        E entity = mapper.fromUpdateDTO(updateDto);
        entity = updateEntity(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public E updateEntity(E entity) {
        preUpdate(entity);
        entity = repository.save(entity);
        postUpdate(entity);
        return entity;
    }

    protected void preUpdate(E entity) {
    }
    protected void postUpdate(E entity) {
    }

    @Transactional(readOnly = true)
    public Optional<D> findOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public void delete(Long id) {
        preDelete(id);
        repository.deleteById(id);
    }

    protected void preDelete(Long id) {
    }

    @Transactional(readOnly = true)
    public List<D> findAll() {
        List<D> res = mapper.toDto(repository.findAll());
        return res;
    }
}