package com.example.calendar.mapper;

import com.example.calendar.entity.CalendarDayEntity;
import com.example.calendar.entity.CalendarMonthEntity;
import com.example.calendar.entity.CalendarTemplateEntity;
import com.example.calendar.model.CalendarDay;
import com.example.calendar.model.CalendarMonth;
import com.example.calendar.model.CalendarTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Преобразует модели календаря в сущности базы данных и обратно
 */
@Component
public class CalendarTemplateMapper {

    /**
     * Преобразует модель шаблона календаря в сущность
     *
     * @param template модель шаблона календаря
     * @return сущность шаблона календаря
     */
    public CalendarTemplateEntity toEntity(CalendarTemplate template) {
        Objects.requireNonNull(
                template,
                "Шаблон календаря не должен быть null"
        );

        CalendarTemplateEntity templateEntity =
                new CalendarTemplateEntity(
                        template.getFirstDayOfYear(),
                        template.isLeapYear()
                );

        for (CalendarMonth month : template.getMonths()) {
            CalendarMonthEntity monthEntity = toEntity(month);
            templateEntity.addMonth(monthEntity);
        }

        return templateEntity;
    }

    /**
     * Преобразует модель календарного месяца в сущность
     *
     * @param month модель календарного месяца
     * @return сущность календарного месяца
     */
    private CalendarMonthEntity toEntity(CalendarMonth month) {
        CalendarMonthEntity monthEntity = new CalendarMonthEntity(month.getMonth().getValue());

        for (CalendarDay day : month.getDays()) {
            CalendarDayEntity dayEntity = toEntity(day);
            monthEntity.addDay(dayEntity);
        }

        return monthEntity;
    }

    /**
     * Преобразует модель календарного дня в сущность
     *
     * @param day модель календарного дня
     * @return сущность календарного дня
     */
    private CalendarDayEntity toEntity(CalendarDay day) {
        return new CalendarDayEntity(
                day.getDayOfMonth(),
                day.getDayOfWeek()
        );
    }

    /**
     * Преобразует сущность шаблона календаря в модель
     *
     * @param templateEntity сущность шаблона календаря
     * @return модель шаблона календаря
     */
    public CalendarTemplate toModel(
            CalendarTemplateEntity templateEntity
    ) {
        Objects.requireNonNull(
                templateEntity,
                "Сущность шаблона календаря не должна быть null"
        );

        List<CalendarMonth> months = templateEntity
                .getMonths()
                .stream()
                .map(this::toModel)
                .toList();

        return new CalendarTemplate(
                templateEntity.getFirstDayOfYear(),
                templateEntity.isLeapYear(),
                months
        );
    }

    /**
     * Преобразует сущность календарного месяца в модель
     *
     * @param monthEntity сущность календарного месяца
     * @return модель календарного месяца
     */
    private CalendarMonth toModel(
            CalendarMonthEntity monthEntity
    ) {
        List<CalendarDay> days = monthEntity
                .getDays()
                .stream()
                .map(this::toModel)
                .toList();

        return new CalendarMonth(
                monthEntity.getMonth(),
                days
        );
    }

    /**
     * Преобразует сущность календарного дня в модель
     *
     * @param dayEntity сущность календарного дня
     * @return модель календарного дня
     */
    private CalendarDay toModel(CalendarDayEntity dayEntity) {
        return new CalendarDay(
                dayEntity.getDayOfMonth(),
                dayEntity.getDayOfWeek()
        );
    }
}