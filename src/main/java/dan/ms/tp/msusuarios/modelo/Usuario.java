package dan.ms.tp.msusuarios.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "USR_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_NAME")
    @NotBlank(message = "Usuario vacío")
    private String userName;

    @NotBlank(message = "Contraseña vacía")
    private String password;

    @Column(name = "CORREO_ELECTRONICO")
    @NotBlank(message = "Email vacío")
    @Email(message = "Formato de email incorrecto")
    private String correoElectronico;

    // @NotNull(message = "Id cliente no debe ser nulo")
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @NotNull(message = "Id tipo usuario no debe ser nulo")
    @Valid
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_USUARIO")
    private TipoUsuario tipoUsuario;
    
}
