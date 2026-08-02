package com.example.calendar.model;

import java.time.DayOfWeek;
import java.util.Objects;

/**
 * Один день календарного месяца
 */
public class CalendarDay {

    /*
    * Номер дня в месяце
    */
    private final int dayOfMonth;

    /*
    * День недели
    */
    private final DayOfWeek dayOfWeek;

    /**
     * Создаёт календарный день
     *
     * @param dayOfMonth порядковый номер дня в месяце
     * @param dayOfWeek день недели
     */
    public CalendarDay(int dayOfMonth, DayOfWeek dayOfWeek) {
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException("Номер дня должен находиться в диапазоне от 1 до 31");
        }

        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "День недели не должен быть null");
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