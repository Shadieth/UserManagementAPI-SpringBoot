package org.islasfilipinas.ApiRESTful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice  // Indica que esta clase maneja excepciones globalmente para los controladores REST
public class ValidacionExceptionHandler {

    // Maneja las excepciones de validación cuando los argumentos no son válidos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Devuelve un código de estado 400 para solicitudes inválidas
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
        // Creamos un mapa para almacenar los errores de validación
        Map<String, String> errors = new HashMap<>();
        
        // Recorremos los errores de validación obtenidos del resultado de enlace de datos
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            // Guardamos el campo que causó el error y el mensaje de error correspondiente
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // Opcionalmente, agregamos un mensaje genérico a los errores
        errors.put("mensaje", "Hubo errores en los datos enviados. Verifique los campos.");

        // Devolvemos la respuesta con el código de estado 400 y el mapa de errores
        return ResponseEntity.badRequest().body(errors);
    }
}
