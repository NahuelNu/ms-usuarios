package dan.ms.tp.msusuarios.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.exception.NotFoundException;
import dan.ms.tp.msusuarios.modelo.Cliente;

@SpringBootTest
public class ClienteServiceTest {
    
    @Autowired
    ClienteService clienteService;

    @MockBean
    ClienteJpaRepository clienteRepo;


    private Cliente clienteMock;
    
    @BeforeEach
    void setUp(){
        this.clienteMock = Cliente.builder()
                            .correoElectronico("a@a.com")
                            .cuit("20123456789")
                            .habilitadoOnline(true)
                            .maximoCuentaCorriente(100.0)
                            .razonSocial("a")
                            .build();

        Mockito.when(clienteRepo.findById(1)).thenReturn(Optional.of(clienteMock));

        Mockito.when(clienteRepo.findById(2)).thenThrow(new NotFoundException("Cliente", 2));
    }

    @Test
    public void buscarPorIdFound(){
        Integer id = 1;

        ResponseEntity<Cliente> clienteEncontrado = clienteService.buscarPorId(id);

        assertEquals(this.clienteMock, clienteEncontrado.getBody());
    }

    @Test
    public void buscarPorIdNotFound(){
        Integer id = 2;

        try {
            ResponseEntity<Cliente> clienteEncontrado = clienteService.buscarPorId(id);

            fail("Se esperaba una excepción NotFoundException pero no se lanzó ninguna excepción.");
        } catch (Exception e) {
            assertEquals(new NotFoundException("Cliente", id),e);
        }
        
    }
}
