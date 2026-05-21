package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Destinos;
import com.example.demo.dto.DestinoDTO;

@Mapper(componentModel = "spring")
public interface DestinoMapper {

    DestinoDTO toDTO(Destinos destinos);

    Destinos toEntity(DestinoDTO destinoDTO);

    List<DestinoDTO> toDTOList(List<Destinos> destinos);

}