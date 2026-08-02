package com.example.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Month;
import java.util.List;
import java.util.Objects;

/**
 * Один календарный месяц
 */
@JsonPropertyOrder({"monthNumber", "monthName", "days"})
public class CalendarMonth {

    /*
     * Месяц года
     */
    private final Month month;

    /*
     * Название месяца на русском языке
     */
    private final String monthName;

    /*
     * Список дней месяца
     */
    private final List<CalendarDay> days;

    /**
     * Создаёт календарный месяц
     *
     * @param month месяц года
     * @param days  список дней месяца
     */
    public CalendarMonth(Month month, List<CalendarDay> days) {
        this.month = Objects.requireNonNull(month, "Месяц не должен быть null");

        Objects.requireNonNull(days, "Список дней не должен быть null");

        if (days.isEmpty()) {
            throw new IllegalArgumentException("Список дней месяца не должен быть пустым");
        }

        if (days.size() < month.minLength() || days.size() > month.maxLength()) {
            throw new IllegalArgumentException("Некорректное количество дней для месяца " + month + ": " + days.size());
        }

        this.monthName = getRussianMonthName(month);
        this.days = List.copyOf(days);
    }

    /**
     * Возвращает месяц года
     *
     * @return месяц года
     */
    @JsonIgnore
    public Month getMonth() {
        return month;
    }

    /**
     * Возвращает номер месяца
     *
     * @return номер месяца
     */
    public int getMonthNumber() {
        return month.getValue();
    }

    /**
     * Возвращает название месяца на русском языке
     *
     * @return название месяца
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     * Возвращает список дней месяца
     *
     * @return список дней месяца
     */
    public List<CalendarDay> getDays() {
        return days;
    }

    /**
     * Возвращает название месяца на русском языке
     *
     * @param month месяц года
     * @return название месяца
     */
    private String getRussianMonthName(Month month) {
        return switch (month) {
            case JANUARY -> "Январь";
            case FEBRUARY -> "Февраль";
            case MARCH -> "Март";
            case APRIL -> "Апрель";
            case MAY -> "Май";
            case JUNE -> "Июнь";
            case JULY -> "Июль";
            case AUGUST -> "Август";
            case SEPTEMBER -> "Сентябрь";
            case OCTOBER -> "Октябрь";
            case NOVEMBER -> "Ноябрь";
            case DECEMBER -> "Декабрь";
        };
    }
}