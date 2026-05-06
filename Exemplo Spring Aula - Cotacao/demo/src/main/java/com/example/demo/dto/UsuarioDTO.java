import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalData;


@Data
@NoArgsConstructor

public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O CPF é obrigatorio")
    @Size(min = 11, max = 11, menssege = "O CPF deve ter 11 caracteres")
    private String CPF;

    @Email(message = "O Email invalido")
    @NotBlank(menssege = "O Email é obrigatorio")
    private String Email;

    @NotBlank(message = "O senha é obrigatorio")
    @Size(min = 6, max = 6, menssege = "O senha deve ter pela menos 6 caracteres")
    private String senha;

    private LocalData dataNascimento;

    @NotBlank(message = "O telefone é obrigatorio")
    private String telefone;

}