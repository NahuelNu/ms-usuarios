package dan.ms.tp.msusuarios.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.NotFoundException;

import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioJpaRepository usuarioRepo;
    @Autowired
    ClienteJpaRepository clienteRepo;

    private final Integer MIN_PASS_LENGHT = 12;
    private final String PASS_ERROR_MSG = "La contraseña debe tener una longitud mínima de 12 caracteres, una minúscula, una mayúscula y un caracter especial";

    @Override
    public ResponseEntity<?> crear(Usuario usuario) {

        Integer clienteId = usuario.getCliente().getId();
        // Ver si lanzar excepción personalizada
        if(clienteId ==null) return ResponseEntity.badRequest().body("Id Cliente nulo");

        Integer tipoUsuarioId = usuario.getTipoUsuario().getId();
        // Ver si lanzar excepción personalizada
        if(tipoUsuarioId ==null) return ResponseEntity.badRequest().body("Id Tipo Usuario nulo");

        // Validar que cliente al que se quiera asociar usuario exista en la bd
        if(!clienteRepo.findById(clienteId).isPresent())
            throw new NotFoundException("Cliente",clienteId);

        Boolean passValida = validatePassword(usuario.getPassword());
    
        if(!passValida)
            // Ver si lanzar excepción personalizada
            return ResponseEntity.badRequest().body(PASS_ERROR_MSG);

        if (usuario.getTipoUsuario().getId().equals(1)) {
            
            Boolean existeAdmin = existeAdmin(usuario.getCliente().getId());
            if (existeAdmin) {
                // Ver si lanzar excepción personalizada
                return ResponseEntity.badRequest().body("Cliente ya tiene asociado un usuario tipo ADMIN");
            }
        }
        
        return ResponseEntity.ok(usuarioRepo.save(usuario));
    }

    @Override
    public ResponseEntity<Usuario> buscarPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepo.findById(id);
        if(!usuario.isPresent()) throw new NotFoundException("Usuario",id);
        return ResponseEntity.ok(usuario.get());
    }

    @Override
    public ResponseEntity<Usuario> borrar(Integer id) {
        Optional<Usuario> usuario = usuarioRepo.findById(id);
        if(!usuario.isPresent()) throw new NotFoundException("Usuario",id);
        
        usuarioRepo.delete(usuario.get());
        return ResponseEntity.ok(usuario.get());
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
    public ResponseEntity<?> modificar(Integer id, Usuario u) {
        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(!usuario.isPresent()) throw new NotFoundException("Usuario",id);

        if(!validatePassword(u.getPassword())){
            // Ver si lanzar excepción personalizada
            return ResponseEntity.badRequest().body(PASS_ERROR_MSG);
        }
        
        Integer tipoUsuarioId = u.getTipoUsuario().getId();
        // Ver si lanzar excepción personalizada
        if(tipoUsuarioId ==null) return ResponseEntity.badRequest().body("Id Tipo Usuario nulo");

        if(u.getTipoUsuario().getId().equals(1)){
            Boolean existeAdmin = existeAdmin(usuario.get().getCliente().getId());
            if (existeAdmin) 
                // Ver si lanzar excepción personalizada
                return ResponseEntity.badRequest().body("Cliente ya tiene asociado un usuario tipo ADMIN");
        }
        
        Usuario updateResponse = usuario.get();
        updateResponse.setCorreoElectronico(u.getCorreoElectronico());
        updateResponse.setPassword(u.getPassword());
        updateResponse.setUserName(u.getUserName());
        updateResponse.setTipoUsuario(u.getTipoUsuario());
        return ResponseEntity.ok(usuarioRepo.save(updateResponse));
    }

    private Boolean existeAdmin(Integer idCliente) {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<Usuario> usuariosFiltrados = usuarios.stream().filter(u->u.getCliente().getId().equals(idCliente)).toList();
        return usuariosFiltrados.stream().anyMatch(u->"ADMIN".equals(u.getTipoUsuario().getTipo()));
    }

    private Boolean validatePassword(String pass){

        if(pass.length()>=MIN_PASS_LENGHT){

        Pattern minuscula = Pattern.compile("[a-z]");
        Pattern mayuscula = Pattern.compile("[A-Z]"); 
        Pattern digito = Pattern.compile("[0-9]");
        Pattern especial = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        //Pattern eight = Pattern.compile (".{8}");


           Matcher tieneMin = minuscula.matcher(pass);
           Matcher tieneMayus = mayuscula.matcher(pass);
           Matcher tieneDigito = digito.matcher(pass);
           Matcher tieneEspecial = especial.matcher(pass);

           return tieneMin.find() && tieneMayus.find() && tieneEspecial.find()
           && tieneDigito.find();

    }
    else
        return false;
    }

    
}