package dan.ms.tp.msusuarios.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.services.ClienteService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteServ;

    @PostMapping()
    public Cliente guardarCliente(@RequestBody Cliente entytyCliente) {
        return clienteServ.crear(entytyCliente);
    }
    
}
