package com.techSolve.transactionAnalyzer.commons;

public enum ReaderType {

    CSV("csv"), XLSX("xlsx");

    private String type;

    private ReaderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
