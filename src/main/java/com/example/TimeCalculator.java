package com.example;

import java.util.Calendar;
import java.util.Set;

public class TimeCalculator {

    private Calendar from;
    private Calendar to;

    private static final Set<Integer> holidays = Set.of(1, 6, 89, 91, 92, 122, 130, 140, 158, 174, 307, 360, 361); // The days number in a year

    public TimeCalculator(long from, long to) {
        if (from > to)
            throw new IllegalArgumentException("Start time can not be more recent than stop time, change position of argument");

        convertTimeFormat(from, to);
    }

    private void convertTimeFormat(long from, long to) {
        this.from = Calendar.getInstance();
        this.from.setTimeInMillis(from);

        this.to = Calendar.getInstance();
        this.to.setTimeInMillis(to);
    }

    public long getNumberOfWorkingMinutes() {
        setCorrectDateAndTime();

        long minutes = countMinutesLeft();
        if (minutes <= 60)
            return minutes;

        long hours = countHours();
        minutes = countMinutesLeft();

        return (hours * 60L) + minutes;
    }

    private long countHours() {
        long hours = 0L;
        while (from.compareTo(to) < 0) {

            while (holidays.contains(from.get(Calendar.DAY_OF_YEAR))) {
                from.add(Calendar.DAY_OF_MONTH, 1);
            }

            if (from.get(Calendar.HOUR_OF_DAY) < 17) {

                if (countMinutesLeft() >= (60L * 9L) && from.get(Calendar.HOUR_OF_DAY) == 8) { // Skip whole workday
                    hours += 9L;
                    from.add(Calendar.HOUR_OF_DAY, 9);

                } else {
                    from.add(Calendar.HOUR_OF_DAY, 1);
                    hours++;
                }

            } else if (from.get(Calendar.HOUR_OF_DAY) == 17) {

                if (from.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    from.add(Calendar.HOUR_OF_DAY,63); // Skip weekend hours
                } else {
                    from.add(Calendar.HOUR_OF_DAY, 15); // Skip night hours
                }
            }
        }
        return hours;
    }

    private long countMinutesLeft() {
        long milliseconds = to.getTimeInMillis() - from.getTimeInMillis();
        return (milliseconds / 1000L) / 60L;
    }

    private void setCorrectDateAndTime() {
        setCorrectHour(from);
        setCorrectHour(to);

        setCorrectDay(from);
        setCorrectDay(to);
    }

    private void setCorrectHour(Calendar calendar) {
        if (calendar.get(Calendar.HOUR_OF_DAY) < 8) {

            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);

        } else if (calendar.get(Calendar.HOUR_OF_DAY) > 17) {

            calendar.set(Calendar.HOUR_OF_DAY, 17);
            calendar.set(Calendar.MINUTE, 0);

        } else if (calendar.get(Calendar.HOUR_OF_DAY) == 17) {
            calendar.set(Calendar.MINUTE, 0);
        }
    }

    private void setCorrectDay(Calendar calendar) {
        while (holidays.contains(from.get(Calendar.DAY_OF_YEAR))) { // If holidays are consecutive
            incrementCalendarDay(calendar, 1);
        }

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            incrementCalendarDay(calendar, 1);

        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            incrementCalendarDay(calendar, 2);
        }
    }

    private void incrementCalendarDay(Calendar calendar, int amount) {
        calendar.add(Calendar.DAY_OF_WEEK, amount);
        setTimeToStartOfWorkDay(calendar);
    }

    private void setTimeToStartOfWorkDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}