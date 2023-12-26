package dan.ms.tp.msusuarios.services;

import java.util.List;

import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteService {
        public Cliente crear(Cliente c);
        public Cliente buscarPorId(Integer id);
            public List<Cliente> buscarPorCuit(String cuit);


}
