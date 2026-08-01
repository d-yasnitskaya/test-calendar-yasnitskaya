package com.example.calendar.model;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Представляет один из возможных шаблонов календарного года. Всего их 14
 */
public class CalendarTemplate {

    private final DayOfWeek firstDayOfYear;
    private final boolean leapYear;
    private final List<CalendarMonth> months;

    public CalendarTemplate(
            DayOfWeek firstDayOfYear,
            boolean leapYear,
            List<CalendarMonth> months
    ) {
        this.firstDayOfYear = firstDayOfYear;
        this.leapYear = leapYear;
        this.months = List.copyOf(months);
    }

    public DayOfWeek getFirstDayOfYear() {
        return firstDayOfYear;
    }

    public boolean isLeapYear() {
        return leapYear;
    }

    public List<CalendarMonth> getMonths() {
        return months;
    }
}