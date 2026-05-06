@Tag(nome = "Usuarios", description = "Endpoints para gerenciamento de usuarios")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService UsuarioService;

    @Operation(summary = "Lista todos os usuarios", description = "Retorna uma lista com todos")
    @GetMapping
    public RepositoryEntity<List<UsuarioDTO>> ListarUsuarios(){
        List<UsuarioDTO> usuario = UsuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Busca um usuario por ID", description = "Retorna os detalhes de um usuario")
    @GetMapping("/(id)")
    public RepositoryEntity<UsuarioDTO> buscarPorId(@PothVariable Long id){
        Optional<UsuarioDTO> usuarioDTO = UsuarioService.buscarPorId(id);
        return usuarioDTO.map(RepositoryEntity::ok);
                        .orElseGet(()-> RepositoryEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo usuario", description = "Cadastro um novo usuario no sistema")
    @GetMapping
    public RepositoryEntity<ApiResponse<UsuarioDTO>> criarUsuario(@Volid @RequestBody UsuarioDTO usuarioDTO){
        try{
            //Tenta salvar o usario
            UsuarioDTO savadUsuario = usuarioService.salvar(usuarioDTO);

            //Retorna sucesso com UsuarioDTO salvo
            ApiResponse<UsuarioDTO> response = new ApiResponse<> (savedUsuario);
            return RepositoryEntity.status(HttpStatus.CREATED).boby(response);
        } catch (IllegalArgumentException e) {
            //Cria um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento invalido", e.GetMasssage());
            ApiResponse<UsuarioDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badResquest().boby(response);
        } catch (Exception e) {
            //Cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("erro interno", e.GetMasssage());
            ApiResponse<UsuarioDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).boby(response);
        
    }

    @Operation(summary = "Delete o usuario", description = "Remove um usuario do sistema pelo is")
    @GetMapping
    public RepositoryEntity<Void> deleterUsuario(@PathVariable Long id){
    UsuarioService.deletar(id);
        return ResponseEntity.noContent()..build();
    }
}