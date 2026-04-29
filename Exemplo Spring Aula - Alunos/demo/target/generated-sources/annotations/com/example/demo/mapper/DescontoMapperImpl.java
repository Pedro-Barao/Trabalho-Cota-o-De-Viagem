package com.example.demo.mapper;

import com.example.demo.Entities.Desconto;
import com.example.demo.dto.DescontoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-29T10:58:20-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DescontoMapperImpl implements DescontoMapper {

    @Override
    public DescontoDTO toDTO(Desconto desconto) {
        if ( desconto == null ) {
            return null;
        }

        DescontoDTO descontoDTO = new DescontoDTO();

        descontoDTO.setId( desconto.getId() );
        descontoDTO.setCotacaoId( desconto.getCotacaoId() );
        descontoDTO.setValorDesconto( desconto.getValorDesconto() );
        descontoDTO.setDescricao( desconto.getDescricao() );
        descontoDTO.setDataAplicacao( desconto.getDataAplicacao() );

        return descontoDTO;
    }

    @Override
    public Desconto toEntity(DescontoDTO descontoDTO) {
        if ( descontoDTO == null ) {
            return null;
        }

        Desconto desconto = new Desconto();

        desconto.setId( descontoDTO.getId() );
        desconto.setCotacaoId( descontoDTO.getCotacaoId() );
        desconto.setValorDesconto( descontoDTO.getValorDesconto() );
        desconto.setDescricao( descontoDTO.getDescricao() );
        desconto.setDataAplicacao( descontoDTO.getDataAplicacao() );

        return desconto;
    }

    @Override
    public List<DescontoDTO> toDTOList(List<Desconto> desconto) {
        if ( desconto == null ) {
            return null;
        }

        List<DescontoDTO> list = new ArrayList<DescontoDTO>( desconto.size() );
        for ( Desconto desconto1 : desconto ) {
            list.add( toDTO( desconto1 ) );
        }

        return list;
    }
}
