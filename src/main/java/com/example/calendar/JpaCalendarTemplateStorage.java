package com.example.calendar.storage;

import com.example.calendar.entity.CalendarTemplateEntity;
import com.example.calendar.exception.CalendarTemplateNotFoundException;
import com.example.calendar.factory.CalendarFactory;
import com.example.calendar.mapper.CalendarTemplateMapper;
import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarTemplateKey;
import com.example.calendar.repository.CalendarTemplateRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

/**
 * Хранилище шаблонов календаря в PostgreSQL
 */
@Component
public class JpaCalendarTemplateStorage implements CalendarTemplateStorage {

    private final CalendarTemplateRepository repository;
    private final CalendarTemplateMapper mapper;
    private final CalendarFactory calendarFactory;

    public JpaCalendarTemplateStorage(CalendarTemplateRepository repository, CalendarTemplateMapper mapper,
            CalendarFactory calendarFactory) {
        this.repository = Objects.requireNonNull(repository, "Репозиторий шаблонов не должен быть null");
        this.mapper = Objects.requireNonNull(mapper, "Маппер шаблонов не должен быть null");
        this.calendarFactory = Objects.requireNonNull(calendarFactory, "Фабрика календаря не должна быть null");
    }

    /**
     * Создаёт 14 шаблонов при первом запуске приложения
     */
    @PostConstruct
    @Transactional
    public void initializeTemplates() {
        if (repository.count() > 0) {
            return;
        }

        for (DayOfWeek firstDayOfYear : DayOfWeek.values()) {
            saveTemplate(firstDayOfYear, false);
            saveTemplate(firstDayOfYear, true);
        }
    }

    /**
     * Возвращает шаблон по ключу
     *
     * @param key ключ шаблона
     * @return шаблон календаря
     */
    @Override
    @Transactional(readOnly = true)
    public CalendarTemplate get(CalendarTemplateKey key) {
        Objects.requireNonNull(key, "Ключ шаблона не должен быть null");

        CalendarTemplateEntity entity = repository
                .findByFirstDayOfYearAndLeapYear(key.firstDayOfYear(), key.leapYear())
                .orElseThrow(() -> new CalendarTemplateNotFoundException("Шаблон календаря не найден: " + key));

        return mapper.toModel(entity);
    }

    /**
     * Возвращает все шаблоны календаря
     *
     * @return список шаблонов
     */
    @Override
    @Transactional(readOnly = true)
    public List<CalendarTemplate> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    /**
     * Создаёт и сохраняет один шаблон
     *
     * @param firstDayOfYear день недели первого января
     * @param leapYear признак високосного года
     */
    private void saveTemplate(DayOfWeek firstDayOfYear, boolean leapYear) {
        CalendarTemplate template = calendarFactory.create(firstDayOfYear, leapYear);

        CalendarTemplateEntity entity = mapper.toEntity(template);

        repository.save(entity);
    }
}