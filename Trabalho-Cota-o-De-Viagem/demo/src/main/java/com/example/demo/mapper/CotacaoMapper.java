package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CotacaoMapper {

    CotacaoDTO toDTO(Cotacao cotacao);

    Cotacao toEntity(CotacaoDTO cotacaoDTO);

    List<CotacaoDTO> toDTOList(List<Cotacao> cotacoes);
}
