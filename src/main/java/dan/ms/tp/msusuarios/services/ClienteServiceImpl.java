package dan.ms.tp.msusuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.exception.NotFoundException;
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
    public ResponseEntity<Cliente> buscarPorId(Integer id) throws NotFoundException{
        Optional<Cliente> cliente = clienteRepo.findById(id);
        if(!cliente.isPresent()) throw new NotFoundException("Cliente",id);
        return ResponseEntity.ok(cliente.get());
    }

    @Override   
    public ResponseEntity<List<Cliente>> buscarPorCuit(String cuit){
        return ResponseEntity.ok(clienteRepo.findByCuit(cuit));
    }

    @Override
    public ResponseEntity<Cliente> borrar(Integer id) {
        Optional<Cliente> cliente = clienteRepo.findById(id);
        if(!cliente.isPresent()) throw new NotFoundException("Cliente",id);
        return ResponseEntity.ok(cliente.get());
    }

    @Override
    public ResponseEntity<Cliente> modificar(Integer id, Cliente c) {
        Optional<Cliente> cli = clienteRepo.findById(id);
        if(!cli.isPresent()) throw new NotFoundException("Cliente",id);
        else{
            
            Cliente updateResponse = cli.get();
            updateResponse.setRazonSocial(c.getRazonSocial());
            updateResponse.setCorreoElectronico(c.getCorreoElectronico());
            updateResponse.setCuit(c.getCuit());
            updateResponse.setHabilitadoOnline(c.getHabilitadoOnline());
            updateResponse.setMaximoCuentaCorriente(c.getMaximoCuentaCorriente());
            return ResponseEntity.ok(clienteRepo.save(updateResponse));
        }
    }

    public ResponseEntity<List<Cliente>> buscarTodos(){
        return ResponseEntity.ok(clienteRepo.findAll());
    }

    public ResponseEntity<Double> getCtaCte(Integer id){
        Optional<Cliente> cliente = clienteRepo.findById(id);
        if(!cliente.isPresent()) throw new NotFoundException("Cliente",id);
        return ResponseEntity.ok(cliente.get().getMaximoCuentaCorriente());
    }
}
