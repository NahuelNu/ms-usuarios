package dan.ms.tp.msusuarios.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
// import lombok.experimental.StandardException;

// @StandardException
@Data
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException{
    private Integer id;
    public NotFoundException(String message, Integer id){
        super(message);
        
        this.id=id;
    }
}
