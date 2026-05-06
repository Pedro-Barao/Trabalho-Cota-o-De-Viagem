import org.mapstruct.Mapper;

import com.example.demo.Entities.Usuario;
import com.example.demo.dto.UsuarioDTO;

@Mapper(componentetModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usurio);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDTOList(List<Usuario> usuarios);

}