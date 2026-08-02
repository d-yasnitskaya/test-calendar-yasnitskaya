package com.example.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.DayOfWeek;
import java.util.Objects;

/**
 * Один день календарного месяца
 */
@JsonPropertyOrder({
        "dayOfMonth",
        "dayOfWeekName"
})
public class CalendarDay {

    /*
     * Номер дня в месяце
     */
    private final int dayOfMonth;

    /*
     * День недели
     */
    private final DayOfWeek dayOfWeek;

    /*
     * Название дня недели на русском языке
     */
    private final String dayOfWeekName;

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
        this.dayOfWeek = Objects.requireNonNull(
                dayOfWeek, "День недели не должен быть null");
        this.dayOfWeekName = getRussianDayOfWeekName(dayOfWeek);
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
    @JsonIgnore
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Возвращает название дня недели на русском языке
     *
     * @return название дня недели
     */
    public String getDayOfWeekName() {
        return dayOfWeekName;
    }

    /**
     * Возвращает название дня недели на русском языке
     *
     * @param dayOfWeek день недели
     * @return название дня недели
     */
    private String getRussianDayOfWeekName(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> "Понедельник";
            case TUESDAY -> "Вторник";
            case WEDNESDAY -> "Среда";
            case THURSDAY -> "Четверг";
            case FRIDAY -> "Пятница";
            case SATURDAY -> "Суббота";
            case SUNDAY -> "Воскресенье";
        };
    }
}