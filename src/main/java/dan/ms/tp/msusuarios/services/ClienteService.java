package dan.ms.tp.msusuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import dan.ms.tp.msusuarios.modelo.Cliente;


public interface ClienteService {
        public ResponseEntity<Cliente> crear(Cliente c);
        public ResponseEntity<Cliente> buscarPorId(Integer id);
        public ResponseEntity<List<Cliente>> buscarPorCuit(String cuit);
        public ResponseEntity<List<Cliente>> buscarTodos();

        //No implementados
        public ResponseEntity<Optional<Cliente>> borrar(Integer id);
        public ResponseEntity<Cliente> modificar(Integer id, Cliente c);
}
