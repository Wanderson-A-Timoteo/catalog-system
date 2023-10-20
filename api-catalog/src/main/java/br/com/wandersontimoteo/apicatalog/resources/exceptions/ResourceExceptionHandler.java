package br.com.wandersontimoteo.apicatalog.resources.exceptions;

import br.com.wandersontimoteo.apicatalog.services.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException error, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimetamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Recurso não encontrado");
        err.setMessage(error.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}