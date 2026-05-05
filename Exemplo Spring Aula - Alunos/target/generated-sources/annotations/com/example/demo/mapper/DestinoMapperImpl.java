package com.example.demo.mapper;

import com.example.demo.Entities.Destinos;
import com.example.demo.dto.DestinoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-05T19:32:30-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DestinoMapperImpl implements DestinoMapper {

    @Override
    public DestinoDTO toDTO(Destinos destinos) {
        if ( destinos == null ) {
            return null;
        }

        DestinoDTO destinoDTO = new DestinoDTO();

        if ( destinos.getId() != null ) {
            destinoDTO.setId( destinos.getId() );
        }
        destinoDTO.setNome( destinos.getNome() );
        destinoDTO.setDescricao( destinos.getDescricao() );
        if ( destinos.getLocalizacao() != null ) {
            destinoDTO.setLocalizacao( Double.parseDouble( destinos.getLocalizacao() ) );
        }

        return destinoDTO;
    }

    @Override
    public Destinos toEntity(DestinoDTO destinoDTO) {
        if ( destinoDTO == null ) {
            return null;
        }

        Destinos destinos = new Destinos();

        destinos.setId( destinoDTO.getId() );
        destinos.setNome( destinoDTO.getNome() );
        destinos.setDescricao( destinoDTO.getDescricao() );
        destinos.setLocalizacao( String.valueOf( destinoDTO.getLocalizacao() ) );

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
