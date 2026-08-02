package com.example.calendar.repository;

import com.example.calendar.entity.CalendarTemplateEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с шаблонами календаря
 */
public interface CalendarTemplateRepository extends JpaRepository<CalendarTemplateEntity, Long> {

    /**
     * Находит шаблон по дню недели первого января
     * и признаку високосного года
     *
     * @param firstDayOfYear день недели первого января
     * @param leapYear признак високосного года
     * @return найденный шаблон
     */
    @EntityGraph(attributePaths = {"months", "months.days"})
    Optional<CalendarTemplateEntity> findByFirstDayOfYearAndLeapYear(
            DayOfWeek firstDayOfYear,
            boolean leapYear
    );

    /**
     * Возвращает все шаблоны вместе с месяцами и днями
     *
     * @return список шаблонов
     */
    @Override
    @EntityGraph(attributePaths = {"months", "months.days"})
    List<CalendarTemplateEntity> findAll();

    /**
     * Проверяет наличие шаблона
     *
     * @param firstDayOfYear день недели первого января
     * @param leapYear признак високосного года
     * @return true если шаблон существует
     */
    boolean existsByFirstDayOfYearAndLeapYear(
            DayOfWeek firstDayOfYear,
            boolean leapYear
    );
}