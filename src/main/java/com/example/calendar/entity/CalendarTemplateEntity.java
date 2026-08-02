package com.example.calendar.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сущность шаблона календарного года
 */
@Entity
@Table(name = "calendar_templates", uniqueConstraints = {
        @UniqueConstraint(name = "uk_template_first_day_leap_year", columnNames = {"first_day_of_year", "leap_year"})
}
)
public class CalendarTemplateEntity {

    /*
     * Идентификатор шаблона календаря
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * День недели первого января
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "first_day_of_year", nullable = false)
    private DayOfWeek firstDayOfYear;

    /*
     * Признак високосного года
     */
    @Column(name = "leap_year", nullable = false)
    private boolean leapYear;

    /*
     * Список месяцев шаблона
     */
    @OneToMany(
            mappedBy = "template",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("monthNumber ASC")
    private List<CalendarMonthEntity> months = new ArrayList<>();

    /**
     * Конструктор для JPA
     */
    protected CalendarTemplateEntity() {
    }

    /**
     * Создаёт сущность шаблона календаря
     *
     * @param firstDayOfYear день недели первого января
     * @param leapYear       признак високосного года
     */
    public CalendarTemplateEntity(DayOfWeek firstDayOfYear, boolean leapYear) {
        this.firstDayOfYear = Objects.requireNonNull(firstDayOfYear, "День недели первого января не должен быть null");
        this.leapYear = leapYear;
    }

    public Long getId() {
        return id;
    }

    public DayOfWeek getFirstDayOfYear() {
        return firstDayOfYear;
    }

    public boolean isLeapYear() {
        return leapYear;
    }

    public List<CalendarMonthEntity> getMonths() {
        return List.copyOf(months);
    }

    /**
     * Добавляет календарный месяц
     *
     * @param month календарный месяц
     */
    public void addMonth(CalendarMonthEntity month) {
        if (month == null) {
            throw new NullPointerException("Календарный месяц не должен быть null");
        }

        month.setTemplate(this);
        months.add(month);
    }
}