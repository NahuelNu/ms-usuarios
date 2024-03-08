package dan.ms.tp.msusuarios.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dan.ms.tp.msusuarios.exception.dto.exceptionDTO;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(NotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<exceptionDTO> handleNotFoundException(NotFoundException ex){
        exceptionDTO responseBody = new exceptionDTO();
        responseBody.setTitle("Resource Not Found");
        responseBody.setStatus(404);
        responseBody.setMessage(ex.getMessage() + " no encontrado" );
        responseBody.setDetail(ex.getMessage() + " con id: "+ex.getId()+" no fue encontrado en la base de datos");
        return ResponseEntity.status(404).body(responseBody);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        exceptionDTO responseBody = new exceptionDTO();
        responseBody.setTitle("Bad Request");
        responseBody.setStatus(status.value());
        responseBody.setMessage("Error de validación en la solicitud" );
        responseBody.setDetail(erroresValidacion(ex));

        return ResponseEntity.status(status).body(responseBody);
    }
    
    private Map<Object, Object> erroresValidacion(MethodArgumentNotValidException ex) {
        Map<Object, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError) error).getField(),error.getDefaultMessage());
        });
        return errors;
    }
}
