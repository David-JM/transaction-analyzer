package demo.transactionAnalyzer.commons;

import lombok.Getter;

@Getter
public enum DateFormat {
    DD_MM_YYYY("dd-MM-yyyy"),
    MM_DD_YYYY("MM-dd-yyyy");

    private String format;

    DateFormat(String format) {
        this.format = format;
    }
}
