package dan.ms.tp.msusuarios.exception.dto;

import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class exceptionDTO {
    //  Ver si queda este formato o se deja más parecido a la respuesta que se envia cuando no pasa la validación
    private HttpStatusCode statusCode;
    private String message;
    private String detail;
}