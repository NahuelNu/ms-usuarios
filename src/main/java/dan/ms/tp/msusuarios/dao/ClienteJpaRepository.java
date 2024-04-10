package dan.ms.tp.msusuarios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente,Integer> {
    
    public List<Cliente> findByCuit(String cuit);

    // No testeada
    @Query("SELECT c FROM Cliente c WHERE c.cuit=:cuit")
    public List<Cliente> findByCuitJPQL(String cuit);
}
