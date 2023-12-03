package dan.ms.tp.msusuarios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente,Integer> {
    
    public List<Cliente> findByCuit(String cuit);
}
