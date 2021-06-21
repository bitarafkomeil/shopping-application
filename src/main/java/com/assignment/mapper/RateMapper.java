package com.assignment.mapper;

import com.assignment.DTO.entity.create.CreateRateDTO;
import com.assignment.DTO.entity.read.RateDTO;
import com.assignment.DTO.entity.update.UpdateRateDTO;
import com.assignment.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for {@link Rate} and its DTOs.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface RateMapper extends BaseMapper<CreateRateDTO, UpdateRateDTO, RateDTO, Rate> {

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "userId",ignore = true)
    @Override
    Rate fromCreateDTO(CreateRateDTO dto);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "userId",ignore = true)
    @Override
    Rate fromUpdateDTO(UpdateRateDTO dto);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.userName", target = "userName")
    @Override
    RateDTO toDto(Rate entity);

    @Override
    List<RateDTO> toDto(List<Rate> entityList);

    default Rate fromId(Long id) {
        if (id == null) return null;
        Rate rate = new Rate();
        rate.setId(id);
        return rate;
    }
}