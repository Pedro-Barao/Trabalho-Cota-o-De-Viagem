package com.example.demo.mapper;

import com.example.demo.Entities.Destinos;
import com.example.demo.dto.DestinoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-20T20:52:16-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260421-0704, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DestinoMapperImpl implements DestinoMapper {

    @Override
    public DestinoDTO toDTO(Destinos destinos) {
        if ( destinos == null ) {
            return null;
        }

        DestinoDTO destinoDTO = new DestinoDTO();

        destinoDTO.setDescricao( destinos.getDescricao() );
        if ( destinos.getId() != null ) {
            destinoDTO.setId( destinos.getId() );
        }
        if ( destinos.getLocalizacao() != null ) {
            destinoDTO.setLocalizacao( Double.parseDouble( destinos.getLocalizacao() ) );
        }
        destinoDTO.setNome( destinos.getNome() );
        destinoDTO.setPrecoPorPessoa( destinos.getPrecoPorPessoa() );

        return destinoDTO;
    }

    @Override
    public Destinos toEntity(DestinoDTO destinoDTO) {
        if ( destinoDTO == null ) {
            return null;
        }

        Destinos destinos = new Destinos();

        destinos.setDescricao( destinoDTO.getDescricao() );
        destinos.setId( destinoDTO.getId() );
        destinos.setLocalizacao( String.valueOf( destinoDTO.getLocalizacao() ) );
        destinos.setNome( destinoDTO.getNome() );
        destinos.setPrecoPorPessoa( destinoDTO.getPrecoPorPessoa() );

        return destinos;
    }

    @Override
    public List<DestinoDTO> toDTOList(List<Destinos> destinos) {
        if ( destinos == null ) {
            return null;
        }

        List<DestinoDTO> list = new ArrayList<DestinoDTO>( destinos.size() );
        for ( Destinos destinos1 : destinos ) {
            list.add( toDTO( destinos1 ) );
        }

        return list;
    }
}
