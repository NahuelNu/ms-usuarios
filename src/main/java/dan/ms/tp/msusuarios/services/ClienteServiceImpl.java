package dan.ms.tp.msusuarios.services;

import java.util.List;
import java.util.Optional;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'modificar'");

        Optional<Cliente> cli = clienteRepo.findById(id);

        if(cli.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            
            Cliente updateResponse = clienteRepo.findById(id).get();
            updateResponse.setRazonSocial(c.getRazonSocial());
            updateResponse.setCorreoElectronico(c.getCorreoElectronico());
            updateResponse.setCuit(c.getCuit());
            updateResponse.setHabilitadoOnline(c.getHabilitadoOnline());
            updateResponse.setMaximoCuentaCorriente(c.getMaximoCuentaCorriente());
            clienteRepo.save(updateResponse);
            return ResponseEntity.ok(updateResponse);
        }
    }

    public ResponseEntity<List<Cliente>> buscarTodos(){

        return ResponseEntity.ok(clienteRepo.findAll());
    }
}
