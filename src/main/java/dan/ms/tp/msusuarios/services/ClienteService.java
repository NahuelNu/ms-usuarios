package dan.ms.tp.msusuarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public class ClienteService {
    
    @Autowired
    ClienteJpaRepository clienteRepo;

    public Cliente crear(Cliente c){
        return clienteRepo.save(c);
    }
}
