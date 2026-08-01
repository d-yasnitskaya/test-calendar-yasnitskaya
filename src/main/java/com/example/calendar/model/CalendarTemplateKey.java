package com.example.calendar.model;

import java.time.DayOfWeek;

/**
 * Ключ для поиска шаблона календаря. Всего 14 шаблонов
 *
 * @param firstDayOfYear день недели первого января
 * @param leapYear признак високосного года
 */
public record CalendarTemplateKey(
        DayOfWeek firstDayOfYear,
        boolean leapYear
) {
}