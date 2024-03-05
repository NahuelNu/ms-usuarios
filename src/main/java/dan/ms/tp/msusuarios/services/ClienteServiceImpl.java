package dan.ms.tp.msusuarios.services;

import java.util.List;
import java.util.Optional;

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
        System.out.println(id);
        return ResponseEntity.of(clienteRepo.findById(id));
    }

    @Override   
    public ResponseEntity<List<Cliente>> buscarPorCuit(String cuit){
        return ResponseEntity.ok(clienteRepo.findByCuit(cuit));
    }

    @Override
    public ResponseEntity<Cliente> borrar(Integer id) {
        Optional<Cliente> c = clienteRepo.findById(id);
        if(c.isPresent()){
            clienteRepo.delete(c.get());
        } 
        return ResponseEntity.of(c);
    }

    @Override
    public ResponseEntity<Cliente> modificar(Integer id, Cliente c) {
        Optional<Cliente> cli = clienteRepo.findById(id);

        if(cli.isEmpty()){
            return ResponseEntity.notFound().build();
        }
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
        Optional<Cliente> c = clienteRepo.findById(id);
        if(c.isPresent()) return ResponseEntity.ok(c.get().getMaximoCuentaCorriente());
        else return ResponseEntity.notFound().build();
    }
}
