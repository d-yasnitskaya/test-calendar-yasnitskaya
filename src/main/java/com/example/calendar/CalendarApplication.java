package com.example.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class, args);
    }
}



/* ТЕСТ
public class CalendarApplication {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Введите выбранный год");
        int year = input.nextInt();
        System.out.println("Выбранный год: " + year);
        input.close();

        boolean isLeapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);

        if (isLeapYear) {
            System.out.println("Год високосный");
        } else {
            System.out.println("Год не високосный");
        }

        LocalDate initialDate = LocalDate.of(year, 1, 1);
        DayOfWeek dayOfTheWeek = initialDate.getDayOfWeek();

        System.out.println("День недели: " + dayOfTheWeek);
        System.out.println(initialDate + " " + dayOfTheWeek);

        int daysInMonth = initialDate.lengthOfMonth();
        int firstDayNumber = dayOfTheWeek.getValue();

        System.out.println("Количество дней в месяце: " + daysInMonth);
        System.out.println("Номер первого дня недели: " + firstDayNumber);

        Month month = initialDate.getMonth();

        String monthName = month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                Locale.forLanguageTag("ru")
        );

        System.out.println(monthName + " " + year);

        System.out.printf("%3s%3s%3s%3s%3s%3s%3s%n", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс");

        for (int i = 1; i < firstDayNumber; i++) {
            System.out.printf("%3s", "");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%3d", day);

            int position = firstDayNumber + day - 1;

            if (position % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();


    }
}

 */