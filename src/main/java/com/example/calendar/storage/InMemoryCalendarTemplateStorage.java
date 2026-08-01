package com.example.calendar.storage;

import com.example.calendar.factory.CalendarFactory;
import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarTemplateKey;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Хранит все 14 возможных шаблонов календарного года
 *
 */
@Component
public class InMemoryCalendarTemplateStorage implements CalendarTemplateStorage {

    private final Map<CalendarTemplateKey, CalendarTemplate> templates;

    /**
     * Создаёт хранилище и заполняет его шаблонами календарного года
     *
     * @param calendarFactory фабрика шаблонов календаря
     */
    public InMemoryCalendarTemplateStorage(CalendarFactory calendarFactory) {
        Objects.requireNonNull(calendarFactory, "Фабрика календарей не должна быть null");

        this.templates = createTemplates(calendarFactory);
    }

    /**
     * Возвращает шаблон календаря по ключу
     *
     * @param key ключ шаблона
     * @return найденный шаблон календаря
     * @throws IllegalArgumentException если шаблон не найден
     */
    @Override
    public CalendarTemplate get(CalendarTemplateKey key) {
        Objects.requireNonNull(key, "Ключ шаблона не должен быть null");

        CalendarTemplate template = templates.get(key);

        if (template == null) {
            throw new IllegalArgumentException("Шаблон календаря не найден: " + key);
        }

        return template;
    }

    /**
     * Создаёт все возможные шаблоны календарного года
     *
     * @param calendarFactory фабрика шаблонов календаря
     * @return карта из 14 шаблонов
     */
    private Map<CalendarTemplateKey, CalendarTemplate> createTemplates(CalendarFactory calendarFactory) {
        Map<CalendarTemplateKey, CalendarTemplate> createdTemplates = new HashMap<>();

        for (DayOfWeek firstDayOfYear : DayOfWeek.values()) {
            addTemplate(createdTemplates, calendarFactory, firstDayOfYear, false);

            addTemplate(createdTemplates, calendarFactory, firstDayOfYear, true);
        }

        return Map.copyOf(createdTemplates);
    }

    /**
     * Создаёт один шаблон календаря и добавляет его в карту
     *
     * @param templates карта шаблонов
     * @param calendarFactory фабрика шаблонов
     * @param firstDayOfYear день недели первого января
     * @param leapYear признак високосного года
     */
    private void addTemplate(Map<CalendarTemplateKey, CalendarTemplate> templates,
            CalendarFactory calendarFactory, DayOfWeek firstDayOfYear, boolean leapYear) {
        CalendarTemplateKey key = new CalendarTemplateKey(firstDayOfYear, leapYear);

        CalendarTemplate template = calendarFactory.create(firstDayOfYear, leapYear);

        templates.put(key, template);
    }
}