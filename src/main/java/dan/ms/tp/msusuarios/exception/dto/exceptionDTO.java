package dan.ms.tp.msusuarios.exception.dto;


import lombok.Data;

@Data
public class exceptionDTO {

    private String title;
    private Integer status;
    private String message;
    private Object detail; //o error?
}