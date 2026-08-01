package com.example.calendar.model;

import java.time.Month;
import java.util.List;

/**
 * Один месяц календарного года
 */
public class CalendarMonth {

    private final Month month;
    private final List<CalendarDay> days;

    /**
     * Создаёт календарный месяц
     *
     * @param month месяц года
     * @param days список дней месяца
     */
    public CalendarMonth(Month month, List<CalendarDay> days) {
        this.month = month;
        this.days = List.copyOf(days);
    }

    /**
     * Возвращает месяц года
     *
     * @return месяц года
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Возвращает неизменяемый список дней месяца
     *
     * @return список календарных дней
     */
    public List<CalendarDay> getDays() {
        return days;
    }
}