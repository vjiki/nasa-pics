package com.github.vjiki.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DateGenerator {

    public void getLastDaysFromNow(List<String> daysList, int days) {
        // Sorting is not working with the following pattern: "yyyy-MM-d"
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-d");

        if (days < 0) {
//            daysList.sort(Comparator.reverseOrder());
            return;
        }



//        String pattern = "yyyy-M-d";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(new Date());

        // Setting format

        // Printing current date
        System.out.println(d.format(LocalDate.now().minusDays(days)));

        daysList.add(d.format(LocalDate.now().minusDays(days)));

        getLastDaysFromNow( daysList, days-1);

        //System.out.println(date);

        //return Collections.emptyList();
    }
}
