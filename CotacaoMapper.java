package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CotacaoMapper {


    //Entity DTO: Cliente/Destinos
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "destino.id", target = "destinoId")
    CotacaoDTO toDTO(Cotacao cotacao);

    //DTO Entity: ignora cliente e destino / Service vai buscar pelos IDs e setar os objetos completos)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "destino", ignore = true)
    Cotacao toEntity(CotacaoDTO cotacaoDTO);

    List<CotacaoDTO> toDTOList(List<Cotacao> cotacoes);

    CotacaoDTO toDTO(Cotacao cotacao);

    Cotacao toEntity(CotacaoDTO cotacaoDTO);

    List<CotacaoDTO> toDTOList(List<Cotacao> cotacoes);
}
