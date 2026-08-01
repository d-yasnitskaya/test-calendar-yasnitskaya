package com.example.calendar.model;

import java.time.Year;

/**
 * Один год
 */
public class CalendarYear {

    private final Year year;
    private final CalendarTemplate template;

    /**
     * Создаёт календарь указанного года на основе готового шаблона
     *
     * @param year год
     * @param template шаблон календаря
     */
    public CalendarYear(Year year, CalendarTemplate template) {
        this.year = year;
        this.template = template;
    }

    /**
     * Возвращает год календаря
     *
     * @return год
     */
    public Year getYear() {
        return year;
    }

    /**
     * Возвращает шаблон календаря
     *
     * @return шаблон календаря
     */
    public CalendarTemplate getTemplate() {
        return template;
    }
}