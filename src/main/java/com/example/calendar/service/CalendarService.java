package com.example.calendar.service;

import com.example.calendar.model.CalendarDay;
import com.example.calendar.model.CalendarMonth;
import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarYear;

import java.util.List;

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

    /**
     * Возвращает указанный месяц выбранного года.
     *
     * @param year        номер года
     * @param monthNumber номер месяца от 1 до 12
     * @return календарный месяц
     */
    CalendarMonth getMonth(int year, int monthNumber);

    /**
     * Возвращает указанный день выбранного месяца и года
     *
     * @param year        номер года
     * @param monthNumber номер месяца от 1 до 12
     * @param dayNumber   номер дня месяца
     * @return календарный день
     */
    CalendarDay getDay(int year, int monthNumber, int dayNumber);

    /**
     * Возвращает все 14 шаблонов календарного года
     *
     * @return список шаблонов календаря
     */
    List<CalendarTemplate> getAllTemplates();
}
