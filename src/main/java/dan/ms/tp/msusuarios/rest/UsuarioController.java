package dan.ms.tp.msusuarios.rest;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.services.UsuarioService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> postUsuario(@RequestBody Usuario u) {
        return usuarioService.crear(u);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuarioById(@PathVariable Integer id){
        return usuarioService.borrar(id);
    }
    
    @GetMapping()
    public ResponseEntity<List<Usuario>> getUsuarioByClienteAndTipo(@RequestParam Integer idCliente,@RequestParam(required = false) Integer idTipo) {
        if(Objects.isNull(idTipo)) return usuarioService.buscarPorClienteId(idCliente);
        else return usuarioService.buscarPorTipoYUsuario(idCliente,idTipo);
    }

    //Falta Put y Patch
    
}
