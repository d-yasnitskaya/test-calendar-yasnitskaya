package com.example.calendar.service;

import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarTemplateKey;
import com.example.calendar.model.CalendarYear;
import com.example.calendar.storage.CalendarTemplateStorage;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

/**
 * Реализация сервиса календаря
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarTemplateStorage templateStorage;
    private static final int MIN_YEAR = 1;
    private static final int MAX_YEAR = 9999;

    /**
     * Создаёт сервис календаря
     *
     * @param templateStorage хранилище шаблонов календаря
     */
    public CalendarServiceImpl(CalendarTemplateStorage templateStorage) {
        this.templateStorage = Objects.requireNonNull(templateStorage, "Хранилище шаблонов не должно быть null");
    }

    /**
     * Возвращает календарь для указанного года
     *
     * @param year номер года
     * @return календарь указанного года
     */
    @Override
    public CalendarYear getCalendar(int year) {
        validateYear(year);
        Year requestedYear = Year.of(year);

        boolean leapYear = requestedYear.isLeap();

        DayOfWeek firstDayOfYear = LocalDate.of(year, 1, 1).getDayOfWeek();

        CalendarTemplateKey key = new CalendarTemplateKey(firstDayOfYear, leapYear);

        CalendarTemplate template = templateStorage.get(key);

        return new CalendarYear(requestedYear, template);
    }
    /**
     * Валидирует допустимый диапазон для года
     *
     * @param year номер года
     * @throws IllegalArgumentException если год находится вне допустимого диапазона
     */
    private void validateYear(int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("Год должен находиться в диапазоне от " + MIN_YEAR + " до " + MAX_YEAR);
        }
    }
}