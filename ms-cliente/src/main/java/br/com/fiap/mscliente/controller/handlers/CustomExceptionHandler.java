package br.com.fiap.mscliente.controller.handlers;
import br.com.fiap.mscliente.dto.ValidationCustomErrorDTO;
import br.com.fiap.mscliente.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFound(ResourceNotFoundException exception,
                                           HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ValidationCustomErrorDTO errorDTO = new ValidationCustomErrorDTO(
                Instant.now(),
                status.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorDTO);
    }
}
