package com.example.calendar.storage;

import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarTemplateKey;

/**
 * Предоставляет доступ к сохранённым шаблонам календаря
 */
public interface CalendarTemplateStorage {

    /**
     * Возвращает шаблон календаря по указанному ключу
     *
     * @param key ключ шаблона
     * @return найденный шаблон календаря
     */
    CalendarTemplate get(CalendarTemplateKey key);
}