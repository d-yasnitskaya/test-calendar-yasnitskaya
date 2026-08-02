package com.example.calendar.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.Objects;

/**
 * Сущность календарного дня
 */
@Entity
@Table(name = "calendar_days")
public class CalendarDayEntity {

    /*
     * Идентификатор календарного дня
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Номер дня в месяце
     */
    @Column(name = "day_of_month", nullable = false)
    private int dayOfMonth;

    /*
     * День недели
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    /*
     * Месяц, к которому относится день
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "month_id", nullable = false)
    private CalendarMonthEntity month;

    /**
     * Конструктор для JPA
     */
    protected CalendarDayEntity() {
    }

    /**
     * Создаёт сущность календарного дня
     *
     * @param dayOfMonth номер дня в месяце
     * @param dayOfWeek день недели
     */
    public CalendarDayEntity(
            int dayOfMonth,
            DayOfWeek dayOfWeek
    ) {
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException("Номер дня должен находиться в диапазоне от 1 до 31");
        }

        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "День недели не должен быть null");
    }

    public Long getId() {
        return id;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public CalendarMonthEntity getMonth() {
        return month;
    }

    /**
     * Устанавливает месяц для календарного дня
     *
     * @param month календарный месяц
     */
    void setMonth(CalendarMonthEntity month) {
        this.month = Objects.requireNonNull(month, "Календарный месяц не должен быть null");
    }
}