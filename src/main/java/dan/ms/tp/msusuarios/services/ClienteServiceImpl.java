package dan.ms.tp.msusuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    ClienteJpaRepository clienteRepo;

    public Cliente crear(Cliente c){
        return clienteRepo.save(c);
    }

    public Cliente buscarPorId(Integer id){
        return clienteRepo.findById(id).orElseThrow();
    }

    public List<Cliente> buscarPorCuit(String cuit){
        return clienteRepo.findByCuit(cuit);
    }
}
