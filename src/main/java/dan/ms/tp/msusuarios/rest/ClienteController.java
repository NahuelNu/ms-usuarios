package dan.ms.tp.msusuarios.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.services.ClienteService;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteServ;

    @PostMapping()
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente entytyCliente) {
        return clienteServ.crear(entytyCliente);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
        return clienteServ.buscarPorId(id);
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> buscarClientePorCuit(@RequestParam String cuit) {
        return clienteServ.buscarPorCuit(cuit);
    }
    
    //Falta Put y Patch
    
    
}
