package com.techSolve.transactionAnalyzer.utility;

import com.techSolve.transactionAnalyzer.commons.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DataFormatter {

    public static LocalDate generateDateWithFormat(final DateFormat dateFormat, final String date) {
        final DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat.getFormat());
        return LocalDate.parse(date, format);
    }
}