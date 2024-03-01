package dan.ms.tp.msusuarios.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import dan.ms.tp.msusuarios.modelo.Usuario;


public interface UsuarioService {
    public ResponseEntity<?> crear(Usuario u);
    public ResponseEntity<Usuario> borrar(Integer id);
    public ResponseEntity<Usuario> buscarPorId(Integer id);
    public ResponseEntity<List<Usuario>> buscarPorClienteId(Integer idCliente);
    public ResponseEntity<List<Usuario>> buscarPorTipoYUsuario(Integer idCliente, Integer idTipo);
    public ResponseEntity<?> modificar(Integer id, Usuario u);
}
