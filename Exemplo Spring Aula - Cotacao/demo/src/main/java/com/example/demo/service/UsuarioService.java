@service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired 
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> listtarTodos() {
        return usuarioMapper.toDTOList(usuarioRepository.fintAll());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDTO);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        return usuarioMapper.toDTOList(usuarioRepository.save(usuario));
    }

    public void deletar(long id) {
        usuarioRepository.deleteById();
    }
}