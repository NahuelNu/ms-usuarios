package dan.ms.tp.msusuarios.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("title", "Bad Request");
        responseBody.put("status", status.value());
        responseBody.put("message", "Error de validación en la solicitud");
        responseBody.put("errors", erroresValidacion(ex));
        //responseBody.put("detail", ex.getAllErrors());

        return ResponseEntity.status(status).headers(headers).body(responseBody);
    }
    
    private Map<Object, Object> erroresValidacion(MethodArgumentNotValidException ex) {
        Map<Object, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError) error).getField(),error.getDefaultMessage());
        });
        return errors;
    }
}
