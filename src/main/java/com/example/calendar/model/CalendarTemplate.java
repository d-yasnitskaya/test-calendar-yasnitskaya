package com.example.calendar.model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

/**
 * Представляет один из возможных шаблонов календарного года. Всего их 14
 */

public class CalendarTemplate {

    private final DayOfWeek firstDayOfYear;
    private final boolean leapYear;
    private final List<CalendarMonth> months;

    public CalendarTemplate(DayOfWeek firstDayOfYear, boolean leapYear, List<CalendarMonth> months) {
        this.firstDayOfYear = Objects.requireNonNull(firstDayOfYear, "Первый день года не должен быть null");

        Objects.requireNonNull(months, "Список месяцев не должен быть null");

        if (months.size() != 12) {
            throw new IllegalArgumentException("Шаблон календаря должен содержать ровно 12 месяцев");
        }

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