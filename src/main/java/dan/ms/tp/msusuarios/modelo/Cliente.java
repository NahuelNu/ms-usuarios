package dan.ms.tp.msusuarios.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "USR_CLIENTES", schema = "dan")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    
    @Column(name = "CUIT")
    @NotBlank(message = "Cuit vacío")
    private String cuit;

    @NotBlank(message = "Email vacío")
    @Email(message = "Formato de email incorrecto")
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    
    @Min(value = 0,message = "El máximo de cuenta corriente debe ser 0 o más")
    @Column(name = "MAX_CTA_CTE")
    private Double maximoCuentaCorriente;

    @Column(name = "HABILITADO_ONLINE")
    private Boolean habilitadoOnline;

    
}
