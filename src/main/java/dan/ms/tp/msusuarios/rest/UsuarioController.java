package dan.ms.tp.msusuarios.rest;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Validated
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> postUsuario(@Valid @RequestBody Usuario u) {
        return usuarioService.crear(u);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable @Min(1) Integer id) {
        return usuarioService.buscarPorId(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuarioById(@PathVariable @Min(1) Integer id){
        return usuarioService.borrar(id);
    }
    
    @GetMapping()
    public ResponseEntity<List<Usuario>> getUsuarioByClienteAndTipo(@RequestParam @Min(1) Integer idCliente,@RequestParam(required = false) Integer idTipo) {
        if(Objects.isNull(idTipo)) return usuarioService.buscarPorClienteId(idCliente);
        else return usuarioService.buscarPorTipoYUsuario(idCliente,idTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> reemplazar(@PathVariable Integer id, 
                                        @Valid @RequestBody Usuario usuario) {
        return usuarioService.modificar(id, usuario);
    } 
    
    
}
