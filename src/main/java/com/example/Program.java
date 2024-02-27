package com.example;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Program {

    public static void main(String[] args) {

        long from = Long.parseLong(args[0]);
        long to = Long.parseLong(args[1]);

        TimeCalculator timeCalculator = new TimeCalculator(from * 1000L, to * 1000L);

        System.out.println("Total working minutes: " + timeCalculator.getNumberOfWorkingMinutes());
    }


}
