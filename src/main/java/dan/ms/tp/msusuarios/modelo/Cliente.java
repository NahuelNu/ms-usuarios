package dan.ms.tp.msusuarios.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "USR_CLIENTES")
@Data
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    
    @NotNull
    private String cuit;

    @NotNull
    @Email
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    
    @Column(name = "MAX_CTA_CTE")
    private Double maximoCuentaCorriente;

    @Column(name = "HABILITADO_ONLINE")
    private Boolean habilitadoOnline;
}
