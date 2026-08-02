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

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Хранилище шаблонов календаря в PostgreSQL
 */
@Component
public class JpaCalendarTemplateStorage implements CalendarTemplateStorage {

    /*
     * Репозиторий шаблонов календаря
     */
    private final CalendarTemplateRepository repository;

    /*
     * Маппер шаблонов календаря
     */
    private final CalendarTemplateMapper mapper;

    /*
     * Фабрика шаблонов календаря
     */
    private final CalendarFactory calendarFactory;

    /*
     * Кеш шаблонов календаря
     */
    private Map<CalendarTemplateKey, CalendarTemplate> cache = Map.of();

    /**
     * Создаёт хранилище шаблонов календаря
     *
     * @param repository      репозиторий шаблонов календаря
     * @param mapper          маппер шаблонов календаря
     * @param calendarFactory фабрика шаблонов календаря
     */
    public JpaCalendarTemplateStorage(
            CalendarTemplateRepository repository,
            CalendarTemplateMapper mapper,
            CalendarFactory calendarFactory) {
        this.repository = Objects.requireNonNull(repository, "Репозиторий шаблонов не должен быть null");

        this.mapper = Objects.requireNonNull(mapper, "Маппер шаблонов не должен быть null");

        this.calendarFactory = Objects.requireNonNull(calendarFactory, "Фабрика календаря не должна быть null");
    }

    /**
     * Создаёт шаблоны и загружает их в кеш
     */
    @PostConstruct
    public void initializeTemplates() {
        if (repository.count() == 0) {
            for (DayOfWeek firstDayOfYear : DayOfWeek.values()) {
                saveTemplate(firstDayOfYear, false);
                saveTemplate(firstDayOfYear, true);
            }
        }

        cache = repository
                .findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toUnmodifiableMap(
                        template -> new CalendarTemplateKey(template.getFirstDayOfYear(),
                                template.isLeapYear()), Function.identity()));
    }

    /**
     * Возвращает шаблон по ключу
     *
     * @param key ключ шаблона
     * @return шаблон календаря
     */
    @Override
    public CalendarTemplate get(CalendarTemplateKey key) {
        Objects.requireNonNull(key, "Ключ шаблона не должен быть null");

        CalendarTemplate template = cache.get(key);

        if (template == null) {
            throw new CalendarTemplateNotFoundException("Шаблон календаря не найден: " + key);
        }

        return template;
    }

    /**
     * Возвращает все шаблоны календаря
     *
     * @return список шаблонов
     */
    @Override
    public List<CalendarTemplate> getAll() {
        return List.copyOf(cache.values());
    }

    /**
     * Создаёт и сохраняет один шаблон
     *
     * @param firstDayOfYear день недели первого января
     * @param leapYear       признак високосного года
     */
    private void saveTemplate(DayOfWeek firstDayOfYear, boolean leapYear) {
        CalendarTemplate template = calendarFactory.create(firstDayOfYear, leapYear);

        CalendarTemplateEntity entity = mapper.toEntity(template);

        repository.save(entity);
    }
}