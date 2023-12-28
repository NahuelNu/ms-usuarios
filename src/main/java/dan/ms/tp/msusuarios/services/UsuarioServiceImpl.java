package dan.ms.tp.msusuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioJpaRepository usuarioRepo;

    @Override
    public ResponseEntity<Usuario> crear(Usuario u) {
        return ResponseEntity.ok(usuarioRepo.save(u));
    }

    @Override
    public ResponseEntity<Usuario> buscarPorId(Integer id) {
        return ResponseEntity.of(usuarioRepo.findById(id));
    }

    @Override
    public ResponseEntity<Usuario> borrar(Integer id) {
        Optional<Usuario> u = usuarioRepo.findById(id);
        if(u.isPresent()){
            usuarioRepo.delete(u.get());
        } 
        return ResponseEntity.of(u);
    }

    @Override
    public ResponseEntity<List<Usuario>> buscarPorClienteId(Integer idCliente) {
        return ResponseEntity.ok(usuarioRepo.findByClienteId(idCliente));
    }

    @Override
    public ResponseEntity<List<Usuario>> buscarPorTipoYUsuario(Integer idCliente, Integer idTipo) {
        List<Usuario> usuarios = usuarioRepo.findByClienteId(idCliente);
        usuarios = usuarios.stream().filter((u)-> u.getTipoUsuario().getId().equals(idTipo)).toList();
        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> modificar(Integer id, Cliente c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    
}