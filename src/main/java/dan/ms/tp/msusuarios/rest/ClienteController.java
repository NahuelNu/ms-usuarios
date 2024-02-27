package dan.ms.tp.msusuarios.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.services.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@RestController
@Validated
@RequestMapping("api/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteServ;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return clienteServ.buscarTodos();
    }
    
//     @RequestMapping(
//         value = "/del/{id}",
//         method={RequestMethod.DELETE,RequestMethod.GET},
//         produces = "application/json")
//     public ResponseEntity<Optional<Cliente>> eliminarCliente(@PathVariable Integer id) {
//         return clienteServ.borrar(id);
//     } 
 
    @PostMapping()
    public ResponseEntity<Cliente> guardarCliente(@RequestBody @Valid Cliente entytyCliente) {
        return clienteServ.crear(entytyCliente);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable @Min(1) Integer id) {
        return clienteServ.buscarPorId(id);
    }

    
    @GetMapping()
    public ResponseEntity<List<Cliente>> buscarClientePorCuit(@RequestParam @NotBlank String cuit) {
        return clienteServ.buscarPorCuit(cuit);
    }
  
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> borrarClientePorId(@PathVariable @Min(1) Integer id){
        return clienteServ.borrar(id);
    }

    @RequestMapping(
        value = "/update/{id}",
        method={RequestMethod.GET,RequestMethod.PUT},
        produces = "application/json")
    public ResponseEntity<Cliente> reemplazar(@PathVariable Integer id, 
    @RequestBody Cliente cliente) {
        //TODO: process PUT request
        
        return clienteServ.modificar(id, cliente);
    }

    @GetMapping("/ctaCte/{id}")
    public ResponseEntity<Double> getCuentaCorriente(@PathVariable Integer id) {
        return clienteServ.getCtaCte(id);
    }
    
    
}
