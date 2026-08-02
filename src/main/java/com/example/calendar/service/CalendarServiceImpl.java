package com.example.calendar.service;

import com.example.calendar.model.CalendarDay;
import com.example.calendar.model.CalendarMonth;
import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarTemplateKey;
import com.example.calendar.model.CalendarYear;
import com.example.calendar.storage.CalendarTemplateStorage;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Реализация сервиса календаря
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarTemplateStorage templateStorage;
    private static final int MIN_YEAR = 1;
    private static final int MAX_YEAR = 9999;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;

    private static final int MIN_DAY = 1;

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
     * Возвращает указанный месяц выбранного года
     *
     * @param year        номер года
     * @param monthNumber номер месяца от 1 до 12
     * @return календарный месяц
     */
    @Override
    public CalendarMonth getMonth(int year, int monthNumber) {
        validateYear(year);
        validateMonth(monthNumber);

        CalendarTemplate template = getTemplate(year);

        return template.getMonths().get(monthNumber - 1);
    }

    /**
     * Возвращает указанный день выбранного месяца и года
     *
     * @param year        номер года
     * @param monthNumber номер месяца от 1 до 12
     * @param dayNumber   номер дня месяца
     * @return календарный день
     */
    @Override
    public CalendarDay getDay(int year, int monthNumber, int dayNumber) {
        CalendarMonth month = getMonth(year, monthNumber);

        validateDay(dayNumber, month);

        return month.getDays().get(dayNumber - 1);
    }

    /**
     * Возвращает все 14 шаблонов календарного года
     *
     * @return список шаблонов календаря
     */
    @Override
    public List<CalendarTemplate> getAllTemplates() {
        return templateStorage.getAll();
    }

    /**
     * Получает подходящий шаблон для указанного года
     *
     * @param year номер года
     * @return шаблон календарного года
     */
    private CalendarTemplate getTemplate(int year) {
        Year requestedYear = Year.of(year);

        boolean leapYear = requestedYear.isLeap();

        DayOfWeek firstDayOfYear = LocalDate.of(year, 1, 1).getDayOfWeek();

        CalendarTemplateKey key = new CalendarTemplateKey(firstDayOfYear, leapYear);

        return templateStorage.get(key);
    }

    /**
     * Проверяет корректность года
     *
     * @param year номер года
     */
    private void validateYear(int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException(
                    "Год должен находиться в диапазоне от " + MIN_YEAR + " до " + MAX_YEAR);
        }
    }

    /**
     * Проверяет корректность номера месяца
     *
     * @param monthNumber номер месяца
     */
    private void validateMonth(int monthNumber) {
        if (monthNumber < MIN_MONTH || monthNumber > MAX_MONTH) {
            throw new IllegalArgumentException("Номер месяца должен находиться в диапазоне от "
                    + MIN_MONTH + " до " + MAX_MONTH);
        }
    }

    /**
     * Проверяет корректность номера дня с учётом месяца
     *
     * @param dayNumber номер дня
     * @param month     календарный месяц
     */
    private void validateDay(
            int dayNumber,
            CalendarMonth month
    ) {
        int maxDay = month.getDays().size();

        if (dayNumber < MIN_DAY || dayNumber > maxDay) {
            throw new IllegalArgumentException(
                    "Номер дня для месяца " + month.getMonth() + " должен находиться в диапазоне от "
                            + MIN_DAY + " до " + maxDay);
        }
    }
}