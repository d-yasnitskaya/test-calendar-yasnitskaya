package com.example.calendar.model;

import java.time.DayOfWeek;

/**
 * Один день календарного месяца
 */
public class CalendarDay {

    private final int dayOfMonth;
    private final DayOfWeek dayOfWeek;

    /**
     * Создаёт календарный день
     *
     * @param dayOfMonth порядковый номер дня в месяце
     * @param dayOfWeek день недели
     */
    public CalendarDay(int dayOfMonth, DayOfWeek dayOfWeek) {
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Возвращает порядковый номер дня в месяце
     *
     * @return номер дня месяца
     */
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * Возвращает день недели
     *
     * @return день недели
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}