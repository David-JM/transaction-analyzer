package com.techSolve.transactionAnalyzer.commons;

public enum DateFormat {
    DD_MM_YYYY("dd-MM-yyyy"),
    MM_DD_YYYY("MM-dd-yyyy");

    private String format;

    private DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
