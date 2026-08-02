package com.example.calendar.exception;

/**
 * Исключение при отсутствии шаблона календаря
 */
public class CalendarTemplateNotFoundException extends RuntimeException {

    /**
     * Создаёт исключение с описанием ошибки
     *
     * @param message описание ошибки
     */
    public CalendarTemplateNotFoundException(String message) {
        super(message);
    }
}