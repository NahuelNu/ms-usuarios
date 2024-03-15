package dan.ms.tp.msusuarios.modelo;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USR_TIPO_USUARIO")
@Data
public class TipoUsuario {

    @Id
    @Range(min = 1,max = 2, message = "Id tipo usuario debe ser 1 (Admin) ó 2 (Empleado)")
    private Integer id;
    private String tipo;
}