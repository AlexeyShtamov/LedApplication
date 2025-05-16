package ru.shtamov.led.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shtamov.led.model.dto.ErrorDto;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDto> handleException(Exception e) {
//        ErrorDto response = new ErrorDto(
//                "error",
//                "Произошла внутренняя ошибка сервера: " + e.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }

    @ExceptionHandler(NoConnectionException.class)
    public ResponseEntity<ErrorDto> handleNoConnectionException(NoConnectionException e) {
        ErrorDto response = new ErrorDto(
                "Ошибка подключения к контроллеру",
                "Нет подключения к микроконтроллеру: " + e.getMessage(),
                LocalDateTime.now()
        );
        log.error(response.message());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}