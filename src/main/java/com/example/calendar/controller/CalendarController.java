package com.example.calendar.controller;

import com.example.calendar.model.CalendarDay;
import com.example.calendar.model.CalendarMonth;
import com.example.calendar.model.CalendarTemplate;
import com.example.calendar.model.CalendarYear;
import com.example.calendar.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-контроллер для работы с календарями
 */
@Tag(
        name = "Calendar",
        description = "API для получения календаря года, месяца, дня и шаблонов календаря"
)
@RestController
@RequestMapping("/calendars")
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * Создаёт контроллер календаря
     *
     * @param calendarService сервис для работы с календарями
     */
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * Возвращает календарь указанного года
     *
     * @param year номер года
     * @return календарь указанного года
     */
    @Operation(
            summary = "Получить календарь года",
            description = "Возвращает календарь указанного года"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Календарь успешно получен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Передан некорректный год"
            )
    })
    @GetMapping("/{year}")
    public CalendarYear getCalendar(
            @Parameter(description = "Номер года от 1 до 9999", example = "2026")
            @PathVariable
            @Min(value = 1, message = "Год должен быть не меньше 1")
            @Max(value = 9999, message = "Год должен быть не больше 9999")
            int year
    ) {
        return calendarService.getCalendar(year);
    }

    /**
     * Возвращает указанный месяц выбранного года
     *
     * @param year        номер года
     * @param monthNumber номер месяца
     * @return календарный месяц
     */
    @Operation(
            summary = "Получить календарный месяц",
            description = "Возвращает указанный месяц выбранного года."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Месяц успешно получен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Передан некорректный год или номер месяца"
            )
    })
    @GetMapping("/{year}/months/{monthNumber}")
    public CalendarMonth getMonth(
            @Parameter(description = "Номер года от 1 до 9999", example = "2026")
            @PathVariable
            @Min(value = 1, message = "Год должен быть не меньше 1")
            @Max(value = 9999, message = "Год должен быть не больше 9999")
            int year,

            @Parameter(description = "Номер месяца от 1 до 12", example = "8")
            @PathVariable
            @Min(value = 1, message = "Номер месяца должен быть не меньше 1")
            @Max(value = 12, message = "Номер месяца должен быть не больше 12")
            int monthNumber
    ) {
        return calendarService.getMonth(year, monthNumber);
    }

    /**
     * Возвращает указанный календарный день
     *
     * @param year        номер года
     * @param monthNumber номер месяца
     * @param dayNumber   номер дня месяца
     * @return календарный день
     */
    @Operation(
            summary = "Получить календарный день",
            description = "Возвращает номер дня месяца и соответствующий ему день недели")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Календарный день успешно получен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Переданы некорректные параметры даты"
            )
    })
    @GetMapping("/{year}/months/{monthNumber}/days/{dayNumber}")
    public CalendarDay getDay(
            @Parameter(description = "Номер года от 1 до 9999", example = "2026")
            @PathVariable
            @Min(value = 1, message = "Год должен быть не меньше 1")
            @Max(value = 9999, message = "Год должен быть не больше 9999")
            int year,

            @Parameter(description = "Номер месяца от 1 до 12", example = "8")
            @PathVariable
            @Min(value = 1, message = "Номер месяца должен быть не меньше 1")
            @Max(value = 12, message = "Номер месяца должен быть не больше 12")
            int monthNumber,

            @Parameter(description = "Номер дня месяца", example = "2")
            @PathVariable
            @Min(value = 1, message = "Номер дня должен быть не меньше 1")
            @Max(value = 31, message = "Номер дня должен быть не больше 31")
            int dayNumber
    ) {
        return calendarService.getDay(year, monthNumber, dayNumber);
    }

    /**
     * Возвращает все 14 возможных шаблонов календарного года
     *
     * @return список календарных шаблонов
     */
    @Operation(
            summary = "Получить все шаблоны календаря",
            description = "Возвращает 14 шаблонов календарного года"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Список шаблонов успешно получен"
    )
    @GetMapping("/templates")
    public List<CalendarTemplate> getAllTemplates() {
        return calendarService.getAllTemplates();
    }
}