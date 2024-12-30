package Enos.SpringProject.forumAlura.infra.exceptions;

import Enos.SpringProject.forumAlura.domain.exceptions.CantAuthenticateException;
import Enos.SpringProject.forumAlura.domain.exceptions.CantGetEnumException;
import Enos.SpringProject.forumAlura.domain.exceptions.CreationTokenException;
import Enos.SpringProject.forumAlura.domain.exceptions.InvalidTokenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CantGetEnumException.class)
    public ResponseEntity<Object> handleError404(CantGetEnumException e){
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidationData::new).toList());
    }

    @ExceptionHandler(CreationTokenException.class)
    public ResponseEntity<Object> handleCreationTokenError(CreationTokenException e){
        return ResponseEntity.internalServerError().body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> handleInvalidTokenError(InvalidTokenException e){
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundError(EntityNotFoundException e){
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCantAuthenticateError(CantAuthenticateException e){
        return ResponseEntity.internalServerError().body(new ErrorDTO(e.getMessage()));
    }

    private record ErrorDTO(String error){
    }

    private record ErrorValidationData(String field, String message){
        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
