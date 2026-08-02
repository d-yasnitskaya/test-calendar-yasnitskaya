package com.example.calendar.entity;

import jakarta.persistence.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность календарного месяца
 */
@Entity
@Table(name = "calendar_months", uniqueConstraints = {
                @UniqueConstraint(name = "uk_template_month_number", columnNames = {"template_id", "month_number"})
        }
)
public class CalendarMonthEntity {

    /*
     * Идентификатор календарного месяца
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Номер месяца
     */
    @Column(name = "month_number", nullable = false)
    private int monthNumber;

    /*
     * Шаблон, к которому относится месяц
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "template_id", nullable = false)
    private CalendarTemplateEntity template;

    /*
     * Список дней месяца
     */
    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dayOfMonth ASC")
    private List<CalendarDayEntity> days = new ArrayList<>();

    /**
     * Конструктор для JPA
     */
    protected CalendarMonthEntity() {
    }

    /**
     * Создаёт сущность календарного месяца
     *
     * @param monthNumber номер месяца
     */
    public CalendarMonthEntity(int monthNumber) {
        validateMonthNumber(monthNumber);
        this.monthNumber = monthNumber;
    }

    public Long getId() {
        return id;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    /**
     * Возвращает месяц года
     *
     * @return месяц года
     */
    @Transient
    public Month getMonth() {
        return Month.of(monthNumber);
    }

    public CalendarTemplateEntity getTemplate() {
        return template;
    }

    public List<CalendarDayEntity> getDays() {
        return List.copyOf(days);
    }

    /**
     * Устанавливает шаблон для календарного месяца
     *
     * @param template шаблон календаря
     */
    void setTemplate(CalendarTemplateEntity template) {
        if (template == null) {
            throw new NullPointerException("Шаблон календаря не должен быть null");
        }

        this.template = template;
    }

    /**
     * Добавляет календарный день
     *
     * @param day календарный день
     */
    public void addDay(CalendarDayEntity day) {
        if (day == null) {
            throw new NullPointerException("Календарный день не должен быть null");
        }

        day.setMonth(this);
        days.add(day);
    }

    /**
     * Проверяет номер месяца
     *
     * @param monthNumber номер месяца
     */
    private void validateMonthNumber(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Номер месяца должен находиться в диапазоне от 1 до 12");
        }
    }
}