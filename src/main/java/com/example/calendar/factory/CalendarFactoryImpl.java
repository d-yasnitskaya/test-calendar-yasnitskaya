package com.example.calendar.factory;

import com.example.calendar.model.CalendarDay;
import com.example.calendar.model.CalendarMonth;
import com.example.calendar.model.CalendarTemplate;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Реализация фабрики календаря из 14 шаблонов
 */

@Component
public class CalendarFactoryImpl implements CalendarFactory {

    /**
     * Создаёт шаблон календарного года
     *
     * @param firstDayOfYear день недели, на который приходится первое января
     * @param leapYear       признак високосного года
     * @return готовый шаблон календаря
     * @throws NullPointerException если день недели не указан
     */
    @Override
    public CalendarTemplate create(DayOfWeek firstDayOfYear, boolean leapYear) {
        Objects.requireNonNull(firstDayOfYear, "День недели не должен быть null");

        List<CalendarMonth> months = new ArrayList<>();

        /*
         * currentDayOfWeek хранит день недели с которого начинается год
         */
        DayOfWeek currentDayOfWeek = firstDayOfYear;

        for (Month month : Month.values()) {
            CalendarMonth calendarMonth = createMonth(month, leapYear, currentDayOfWeek);

            months.add(calendarMonth);

            currentDayOfWeek = calculateNextMonthFirstDay(currentDayOfWeek, month.length(leapYear));
        }

        return new CalendarTemplate(firstDayOfYear, leapYear, months);
    }

    /**
     * Создаёт один календарный месяц
     *
     * @param month           месяц года
     * @param leapYear        признак високосного года
     * @param firstDayOfMonth день недели первого числа месяца
     * @return созданный календарный месяц
     */
    private CalendarMonth createMonth(Month month, boolean leapYear, DayOfWeek firstDayOfMonth) {
        int daysInMonth = month.length(leapYear);

        List<CalendarDay> days = createDays(daysInMonth, firstDayOfMonth);

        return new CalendarMonth(month, days);
    }

    /**
     * Создаёт список дней месяца
     *
     * @param daysInMonth     количество дней в месяце
     * @param firstDayOfMonth день недели первого числа месяца
     * @return список календарных дней
     */
    private List<CalendarDay> createDays(int daysInMonth, DayOfWeek firstDayOfMonth) {
        List<CalendarDay> days = new ArrayList<>(daysInMonth);

        DayOfWeek currentDayOfWeek = firstDayOfMonth;

        for (int dayNumber = 1; dayNumber <= daysInMonth; dayNumber++) {
            CalendarDay calendarDay = new CalendarDay(dayNumber, currentDayOfWeek);

            days.add(calendarDay);

            currentDayOfWeek = currentDayOfWeek.plus(1);
        }

        return days;
    }

    /**
     * Рассчитывает день недели первого числа следующего месяца
     *
     * @param firstDayOfMonth день недели первого числа текущего месяца
     * @param daysInMonth     количество дней в текущем месяце
     * @return день недели первого числа следующего месяца
     */
    private DayOfWeek calculateNextMonthFirstDay(DayOfWeek firstDayOfMonth, int daysInMonth) {
        return firstDayOfMonth.plus(daysInMonth);
    }
}