package com.example.calendar.factory;

import com.example.calendar.model.CalendarMonth;
import com.example.calendar.model.CalendarTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты фабрики календарных шаблонов
 */
class CalendarFactoryImplTest {

    private CalendarFactory calendarFactory;

    /**
     * Создаёт фабрику перед каждым тестом
     */
    @BeforeEach
    void setUp() {
        calendarFactory = new CalendarFactoryImpl();
    }

    /**
     * Проверяет создание двенадцати месяцев
     */
    @Test
    void shouldCreateTwelveMonths() {
        CalendarTemplate template = calendarFactory.create(DayOfWeek.MONDAY, false);

        assertEquals(12, template.getMonths().size());
    }

    /**
     * Проверяет количество дней в обычном году
     */
    @Test
    void shouldCreate365DaysForCommonYear() {
        CalendarTemplate template = calendarFactory.create(DayOfWeek.MONDAY, false);

        int daysCount = template.getMonths().stream().mapToInt(month -> month.getDays().size()).sum();

        assertEquals(365, daysCount);
        assertFalse(template.isLeapYear());
    }

    /**
     * Проверяет количество дней в високосном году
     */
    @Test
    void shouldCreate366DaysForLeapYear() {
        CalendarTemplate template = calendarFactory.create(DayOfWeek.MONDAY, true);

        int daysCount = template.getMonths()
                .stream()
                .mapToInt(month -> month.getDays().size())
                .sum();

        assertEquals(366, daysCount);
        assertTrue(template.isLeapYear());
    }

    /**
     * Проверяет первый день года
     */
    @Test
    void shouldStartYearWithSpecifiedDayOfWeek() {
        CalendarTemplate template = calendarFactory.create(
                DayOfWeek.THURSDAY,
                false
        );

        CalendarMonth january = template.getMonths().get(0);

        assertEquals(DayOfWeek.THURSDAY, january.getDays().get(0).getDayOfWeek());
    }
}