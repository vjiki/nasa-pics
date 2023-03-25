package com.github.vjiki.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateGenerator {

    public void getLastDaysFromNow(List<String> daysList, int days) {
        // Sorting is not working with the following pattern: "yyyy-M-d"
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (days < 0) {
            return;
        }
        daysList.add(d.format(LocalDate.now().minusDays(days)));
        getLastDaysFromNow(daysList, days-1);
    }
}
