package dan.ms.tp.msusuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    ClienteJpaRepository clienteRepo;


    @Override
    public ResponseEntity<Cliente> crear(Cliente c){
        return ResponseEntity.ok(clienteRepo.save(c)); 
    }

    @Override
    public ResponseEntity<Cliente> buscarPorId(Integer id){
        return ResponseEntity.of(clienteRepo.findById(id));
    }

    @Override   
    public ResponseEntity<List<Cliente>> buscarPorCuit(String cuit){
        return ResponseEntity.ok(clienteRepo.findByCuit(cuit));
    }

    @Override
    public ResponseEntity<Cliente> borrar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

    @Override
    public ResponseEntity<Cliente> modificar(Integer id, Cliente c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }
}
