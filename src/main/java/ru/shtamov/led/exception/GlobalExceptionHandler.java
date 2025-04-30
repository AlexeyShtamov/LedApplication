package ru.shtamov.led.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shtamov.led.model.SettingsResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SettingsResponseDTO> handleException(Exception e) {
        SettingsResponseDTO response = new SettingsResponseDTO();
        response.setStatus("error");
        response.setMessage("Произошла внутренняя ошибка сервера: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}