package com.assignment.mapper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <CD> - DTO type parameter.
 * @param <UD> - DTO type parameter.
 * @param <D>  - DTO type parameter.
 * @param <E>  - Entity type parameter.
 */
public interface BaseMapper<CD, UD, D, E> {

    E fromCreateDTO(CD dto);

    E fromUpdateDTO(UD dto);

    D toDto(E entity);

    List<D> toDto(List<E> entityList);
}