package dan.ms.tp.msusuarios.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.services.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClienteService clienteService;

    Cliente clienteMock;

    @BeforeEach
    void setUp(){
        this.clienteMock = Cliente.builder()
                            .id(1)
                            .correoElectronico("a@a.com")
                            .cuit("20123456789")
                            .habilitadoOnline(true)
                            .maximoCuentaCorriente(100.0)
                            .razonSocial("a")
                            .build();    
    }

    @Test
    public void guardarClienteTest() throws Exception{
        Mockito.when(clienteService.crear(Mockito.any(Cliente.class))).thenReturn(ResponseEntity.ok(this.clienteMock));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cliente")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(asJsonString(this.clienteMock)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.correoElectronico", Matchers.is("a@a.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cuit", Matchers.is("20123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.habilitadoOnline", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.maximoCuentaCorriente", Matchers.is(100.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.razonSocial", Matchers.is("a")));
                
    }

    // Convertir un objeto a JSON
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
