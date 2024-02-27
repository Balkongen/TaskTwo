package com.example;

import com.example.TimeCalculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculatorTest {


    @Test
    void getNumberOfWorkingMinutes_shouldBeSixty_sameDay() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,9);
        to.set(Calendar.MINUTE,0);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);

        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());

        assertEquals(60, timeCalculator.getNumberOfWorkingMinutes());
    }

    @Test
    void getNumberOfWorkingMinutes_shouldBeOne_sameDay() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,8);
        to.set(Calendar.MINUTE,1);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);

        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());

        assertEquals(1, timeCalculator.getNumberOfWorkingMinutes());
    }

    @Test
    void getNumberOfWorkingMinutes_shouldBeZero_sameDay() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,8);
        to.set(Calendar.MINUTE,0);
        to.set(Calendar.SECOND,59);
        to.set(Calendar.MILLISECOND,0);

        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());
        long diff = timeCalculator.getNumberOfWorkingMinutes();
        assertEquals(0, diff);
    }

    @Test
    void getNumberOfWorkingMinutes_shouldBeOne_setToOneMinuteAndFiftyNineSeconds_sameDay() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,8);
        to.set(Calendar.MINUTE,1);
        to.set(Calendar.SECOND,59);
        to.set(Calendar.MILLISECOND,0);

        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());
        long diff = timeCalculator.getNumberOfWorkingMinutes();
        assertEquals(1, diff);
    }

    @Test
    void getNumberOfWorkingMinutes_fiveHundredAndThirtyNine_sameDay() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,16);
        to.set(Calendar.MINUTE,59);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);


        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());
        long diff = timeCalculator.getNumberOfWorkingMinutes();

        assertEquals(539, diff);
    }

    @Test
    void getNumberOfWorkingMinutes_fiveHundredAndForty_sameDay() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,17);
        to.set(Calendar.MINUTE,0);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);


        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());
        long diff = timeCalculator.getNumberOfWorkingMinutes();

        assertEquals(540, diff);
    }

    @Test
    void getNumberOfWorkingMinutes_one_differentDays() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 5);
        from.set(Calendar.HOUR_OF_DAY,17);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 6);
        to.set(Calendar.HOUR_OF_DAY,8);
        to.set(Calendar.MINUTE,1);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);


        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());
        long diff = timeCalculator.getNumberOfWorkingMinutes();

        assertEquals(1, diff);
    }

    @Test
    void getNumberOfWorkingMinutes_one_overWeekend() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 2);
        from.set(Calendar.HOUR_OF_DAY,17);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,8);
        to.set(Calendar.MINUTE,1);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);

        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());
        long diff = timeCalculator.getNumberOfWorkingMinutes();

        assertEquals(1, diff);
    }

    @Test
    void shouldThrowException_wrongPositionOfArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
           new TimeCalculator(1, 0);
        });
    }


    @Test
    void timeCalculator_shouldShiftDate() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 3);
        from.set(Calendar.HOUR_OF_DAY,17);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 5);
        to.set(Calendar.HOUR_OF_DAY,9);
        to.set(Calendar.MINUTE,0);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);

        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());


        assertEquals(60, timeCalculator.getNumberOfWorkingMinutes());
    }

    @Test
    void getNumberOfWorkingMinutes_one_overMonth() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_MONTH, 29);
        from.set(Calendar.HOUR_OF_DAY,17);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_MONTH, 1);
        to.set(Calendar.MONTH, 2);
        to.set(Calendar.HOUR_OF_DAY,8);
        to.set(Calendar.MINUTE,1);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);


        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());


        assertEquals(1, timeCalculator.getNumberOfWorkingMinutes());
    }

    @Test
    void getNumberOfWorkingMinutes_one_fromDateOnHoliday() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_YEAR, 1);
        from.set(Calendar.HOUR_OF_DAY, 8);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_YEAR, 2);
        to.set(Calendar.HOUR_OF_DAY,9);
        to.set(Calendar.MINUTE,0);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);


        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());


        assertEquals(60, timeCalculator.getNumberOfWorkingMinutes());
    }


    @Test
    void getNumberOfWorkingMinutes_one_overHoliday() {
        Calendar from = Calendar.getInstance();
        from.set(Calendar.DAY_OF_YEAR, 157);
        from.set(Calendar.HOUR_OF_DAY, 16);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);

        Calendar to = Calendar.getInstance();
        to.set(Calendar.DAY_OF_YEAR, 159);
        to.set(Calendar.HOUR_OF_DAY, 9);
        to.set(Calendar.MINUTE,0);
        to.set(Calendar.SECOND,0);
        to.set(Calendar.MILLISECOND,0);


        System.out.println(from.getTime());
        System.out.println(to.getTime());
        System.out.println("---------");
        TimeCalculator timeCalculator = new TimeCalculator(from.getTimeInMillis(), to.getTimeInMillis());

        long diff = timeCalculator.getNumberOfWorkingMinutes();
        assertEquals(120, diff);
    }
}