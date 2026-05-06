package com.example.demo.mapper;

import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD:Exemplo Spring Aula - Cotacao/target/generated-sources/annotations/com/example/demo/mapper/PagamentoMapperImpl.java
    date = "2026-05-06T07:45:02-0300",
=======
    date = "2026-05-06T10:57:36-0300",
>>>>>>> 69bdf644960c179b5c7e7c7dae3ec9f8d584c785:Exemplo Spring Aula - Alunos/target/generated-sources/annotations/com/example/demo/mapper/PagamentoMapperImpl.java
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class PagamentoMapperImpl implements PagamentoMapper {

    @Override
    public PagamentoDTO toDTO(Pagamento pagamento) {
        if ( pagamento == null ) {
            return null;
        }

        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        pagamentoDTO.setCotacaoId( pagamento.getCotacaoId() );
        pagamentoDTO.setDataPagamento( pagamento.getDataPagamento() );
        pagamentoDTO.setId( pagamento.getId() );
        pagamentoDTO.setStatus( pagamento.getStatus() );
        pagamentoDTO.setValorPago( pagamento.getValorPago() );

        return pagamentoDTO;
    }

    @Override
    public Pagamento toEntity(PagamentoDTO pagamentoDTO) {
        if ( pagamentoDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setCotacaoId( pagamentoDTO.getCotacaoId() );
        pagamento.setDataPagamento( pagamentoDTO.getDataPagamento() );
        pagamento.setId( pagamentoDTO.getId() );
        pagamento.setStatus( pagamentoDTO.getStatus() );
        pagamento.setValorPago( pagamentoDTO.getValorPago() );

        return pagamento;
    }

    @Override
    public List<PagamentoDTO> toDTOList(List<Pagamento> pagamentos) {
        if ( pagamentos == null ) {
            return null;
        }

        List<PagamentoDTO> list = new ArrayList<PagamentoDTO>( pagamentos.size() );
        for ( Pagamento pagamento : pagamentos ) {
            list.add( toDTO( pagamento ) );
        }

        return list;
    }
}
