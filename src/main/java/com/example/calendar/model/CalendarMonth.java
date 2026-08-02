package com.example.calendar.model;

import java.time.Month;
import java.util.List;
import java.util.Objects;

/**
 * Один месяц календарного года
 */
public class CalendarMonth {

    /*
    * Месяц года
    */
    private final Month month;

    /*
    * Список дней месяца
    */
    private final List<CalendarDay> days;

    public CalendarMonth(Month month, List<CalendarDay> days) {
        this.month = Objects.requireNonNull(month, "Месяц не должен быть null");

        Objects.requireNonNull(days, "Список дней не должен быть null");

        if (days.isEmpty()) {
            throw new IllegalArgumentException("Список дней месяца не должен быть пустым");
        }

        int expectedDaysCount = month.maxLength();

        if (days.size() < month.minLength() || days.size() > expectedDaysCount) {
            throw new IllegalArgumentException("Некорректное количество дней для месяца " + month + ": " + days.size());
        }

        this.days = List.copyOf(days);
    }

    public Month getMonth() {
        return month;
    }

    public List<CalendarDay> getDays() {
        return days;
    }
}