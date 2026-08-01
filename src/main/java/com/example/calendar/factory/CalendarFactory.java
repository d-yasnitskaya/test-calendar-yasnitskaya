package com.example.calendar.factory;

import com.example.calendar.model.CalendarTemplate;

import java.time.DayOfWeek;

/**
 * Создаёт шаблоны календарного года
 */
public interface CalendarFactory {

    /**
     * Создаёт шаблон календаря.
     *
     * @param firstDayOfYear день недели первого января
     * @param leapYear признак високосного года
     * @return шаблон календарного года
     */
    CalendarTemplate create(DayOfWeek firstDayOfYear, boolean leapYear);
}