package com.example.calendar.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

/**
 * Глобальный обработчик исключений приложения
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает отсутствие шаблона календаря
     *
     * @param exception возникшее исключение
     * @param request HTTP-запрос
     * @return информация об ошибке
     */
    @ExceptionHandler(CalendarTemplateNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTemplateNotFound(
            CalendarTemplateNotFoundException exception,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    /**
     * Обрабатывает ошибки проверки входных данных
     *
     * @param exception возникшее исключение
     * @param request HTTP-запрос
     * @return информация об ошибке
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException exception,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    /**
     * Обрабатывает ошибки аннотаций валидации
     *
     * @param exception возникшее исключение
     * @param request HTTP-запрос
     * @return информация об ошибке
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            HandlerMethodValidationException exception,
            HttpServletRequest request
    ) {
        String message = exception
                .getAllErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Переданы некорректные параметры");

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                message,
                request.getRequestURI()
        );
    }

    /**
     * Обрабатывает неверный тип параметра
     *
     * @param exception возникшее исключение
     * @param request HTTP-запрос
     * @return информация об ошибке
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request
    ) {
        String message = "Параметр "
                + exception.getName()
                + " имеет некорректное значение";

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                message,
                request.getRequestURI()
        );
    }

    /**
     * Обрабатывает остальные ошибки приложения
     *
     * @param exception возникшее исключение
     * @param request HTTP-запрос
     * @return информация об ошибке
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception exception,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла внутренняя ошибка сервера",
                request.getRequestURI()
        );
    }

    /**
     * Формирует ответ с информацией об ошибке
     *
     * @param status HTTP-статус
     * @param message описание ошибки
     * @param path адрес запроса
     * @return информация об ошибке
     */
    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            String path
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }
}