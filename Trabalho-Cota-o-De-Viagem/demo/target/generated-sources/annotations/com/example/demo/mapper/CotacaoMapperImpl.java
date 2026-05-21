package com.example.demo.mapper;

import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-20T20:52:17-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260421-0704, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class CotacaoMapperImpl implements CotacaoMapper {

    @Override
    public CotacaoDTO toDTO(Cotacao cotacao) {
        if ( cotacao == null ) {
            return null;
        }

        CotacaoDTO cotacaoDTO = new CotacaoDTO();

        cotacaoDTO.setClienteId( cotacao.getClienteId() );
        cotacaoDTO.setDataCotacao( cotacao.getDataCotacao() );
        cotacaoDTO.setDataRetorno( cotacao.getDataRetorno() );
        cotacaoDTO.setDataViagem( cotacao.getDataViagem() );
        cotacaoDTO.setDestinoId( cotacao.getDestinoId() );
        cotacaoDTO.setId( cotacao.getId() );
        cotacaoDTO.setNumeroDePessoas( cotacao.getNumeroDePessoas() );
        cotacaoDTO.setStatus( cotacao.getStatus() );
        cotacaoDTO.setValorTotal( cotacao.getValorTotal() );

        return cotacaoDTO;
    }

    @Override
    public Cotacao toEntity(CotacaoDTO cotacaoDTO) {
        if ( cotacaoDTO == null ) {
            return null;
        }

        Cotacao cotacao = new Cotacao();

        cotacao.setClienteId( cotacaoDTO.getClienteId() );
        cotacao.setDataCotacao( cotacaoDTO.getDataCotacao() );
        cotacao.setDataRetorno( cotacaoDTO.getDataRetorno() );
        cotacao.setDataViagem( cotacaoDTO.getDataViagem() );
        cotacao.setDestinoId( cotacaoDTO.getDestinoId() );
        cotacao.setId( cotacaoDTO.getId() );
        cotacao.setNumeroDePessoas( cotacaoDTO.getNumeroDePessoas() );
        cotacao.setStatus( cotacaoDTO.getStatus() );
        cotacao.setValorTotal( cotacaoDTO.getValorTotal() );

        return cotacao;
    }

    @Override
    public List<CotacaoDTO> toDTOList(List<Cotacao> cotacoes) {
        if ( cotacoes == null ) {
            return null;
        }

        List<CotacaoDTO> list = new ArrayList<CotacaoDTO>( cotacoes.size() );
        for ( Cotacao cotacao : cotacoes ) {
            list.add( toDTO( cotacao ) );
        }

        return list;
    }
}
