package com.example.calendar.service;

import com.example.calendar.factory.CalendarFactory;
import com.example.calendar.factory.CalendarFactoryImpl;
import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.storage.CalendarTemplateStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тесты сервиса календаря
 */
class CalendarServiceImplTest {

    private CalendarTemplateStorage templateStorage;
    private CalendarService calendarService;
    private CalendarFactory calendarFactory;

    /**
     * Создаёт зависимости перед каждым тестом
     */
    @BeforeEach
    void setUp() {
        templateStorage = mock(CalendarTemplateStorage.class);
        calendarService = new CalendarServiceImpl(templateStorage);
        calendarFactory = new CalendarFactoryImpl();
    }

    /**
     * Проверяет получение всех шаблонов
     */
    @Test
    void shouldReturnAllTemplates() {
        List<CalendarTemplate> templates = List.of(calendarFactory.create(DayOfWeek.MONDAY, false),
                calendarFactory.create(DayOfWeek.MONDAY, true));

        when(templateStorage.getAll()).thenReturn(templates);

        List<CalendarTemplate> result = calendarService.getAllTemplates();

        assertEquals(2, result.size());
        assertFalse(result.get(0).isLeapYear());
        assertTrue(result.get(1).isLeapYear());

        verify(templateStorage).getAll();
    }
}