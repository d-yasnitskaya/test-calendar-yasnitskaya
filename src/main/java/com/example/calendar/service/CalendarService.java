package com.example.calendar.service;

import com.example.calendar.model.CalendarYear;

/**
 * Предоставляет операции для работы с календарями
 */
public interface CalendarService {

    /**
     * Возвращает календарь для указанного года
     *
     * @param year номер года
     * @return календарь указанного года
     */
    CalendarYear getCalendar(int year);
}