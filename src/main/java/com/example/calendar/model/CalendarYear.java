package com.example.calendar.model;

import java.time.Year;
import java.util.Objects;

/**
 * Один год
 */
public class CalendarYear {

    private final Year year;
    private final CalendarTemplate template;

    public CalendarYear(Year year, CalendarTemplate template) {
        this.year = Objects.requireNonNull(year, "Год не должен быть null");

        this.template = Objects.requireNonNull(template, "Шаблон календаря не должен быть null");
    }

    public Year getYear() {
        return year;
    }

    public CalendarTemplate getTemplate() {
        return template;
    }
}