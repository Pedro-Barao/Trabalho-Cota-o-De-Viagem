package com.example.demo.mapper;

import com.example.demo.Entities.Desconto;
import com.example.demo.dto.DescontoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-05T19:28:04-0300",
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

        descontoDTO.setCotacaoId( desconto.getCotacaoId() );
        descontoDTO.setDataAplicacao( desconto.getDataAplicacao() );
        descontoDTO.setDescricao( desconto.getDescricao() );
        descontoDTO.setId( desconto.getId() );
        descontoDTO.setValorDesconto( desconto.getValorDesconto() );

        return descontoDTO;
    }

    @Override
    public Desconto toEntity(DescontoDTO descontoDTO) {
        if ( descontoDTO == null ) {
            return null;
        }

        Desconto desconto = new Desconto();

        desconto.setCotacaoId( descontoDTO.getCotacaoId() );
        desconto.setDataAplicacao( descontoDTO.getDataAplicacao() );
        desconto.setDescricao( descontoDTO.getDescricao() );
        desconto.setId( descontoDTO.getId() );
        desconto.setValorDesconto( descontoDTO.getValorDesconto() );

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
