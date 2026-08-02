package com.example.calendar.storage;

import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarTemplateKey;

import java.util.List;

/**
 * Хранилище шаблонов календаря
 */
public interface CalendarTemplateStorage {

    /**
     * Возвращает шаблон по ключу
     *
     * @param key ключ шаблона
     * @return шаблон календаря
     */
    CalendarTemplate get(CalendarTemplateKey key);

    /**
     * Возвращает все шаблоны календаря
     *
     * @return список шаблонов
     */
    List<CalendarTemplate> getAll();
}