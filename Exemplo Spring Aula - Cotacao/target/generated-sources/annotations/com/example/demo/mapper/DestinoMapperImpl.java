package com.example.demo.mapper;

import com.example.demo.Entities.Destinos;
import com.example.demo.dto.DestinoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD:Exemplo Spring Aula - Cotacao/target/generated-sources/annotations/com/example/demo/mapper/DestinoMapperImpl.java
    date = "2026-05-06T07:45:00-0300",
=======
<<<<<<< HEAD
    date = "2026-05-05T19:07:49-0300",
=======
    date = "2026-05-05T20:27:37-0300",
>>>>>>> e6098382a48e49a4ab758fd66b86b9c66b17491c
>>>>>>> 69bdf644960c179b5c7e7c7dae3ec9f8d584c785:Exemplo Spring Aula - Alunos/target/generated-sources/annotations/com/example/demo/mapper/DestinoMapperImpl.java
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

        destinoDTO.setDescricao( destinos.getDescricao() );
        if ( destinos.getId() != null ) {
            destinoDTO.setId( destinos.getId() );
        }
        if ( destinos.getLocalizacao() != null ) {
            destinoDTO.setLocalizacao( Double.parseDouble( destinos.getLocalizacao() ) );
        }
        destinoDTO.setNome( destinos.getNome() );

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
