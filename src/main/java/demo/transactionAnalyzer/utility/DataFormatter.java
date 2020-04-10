package demo.transactionAnalyzer.utility;

import demo.transactionAnalyzer.commons.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DataFormatter {

    private DataFormatter() {
    }

    public static LocalDate generateDateWithFormat(final DateFormat dateFormat, final String date) {
        final DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat.getFormat());
        return LocalDate.parse(date, format);
    }
}