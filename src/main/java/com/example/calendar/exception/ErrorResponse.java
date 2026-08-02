package com.example.calendar.exception;

import java.time.LocalDateTime;

/**
 * Ответ с информацией об ошибке
 *
 * @param timestamp дата и время возникновения ошибки
 * @param status HTTP-статус
 * @param error название ошибки
 * @param message описание ошибки
 * @param path адрес запроса
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}