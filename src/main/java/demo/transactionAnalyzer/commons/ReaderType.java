package demo.transactionAnalyzer.commons;

import lombok.Getter;

@Getter
public enum ReaderType {

    CSV("csv"), XLSX("xlsx");

    private String type;

    ReaderType(String type) {
        this.type = type;
    }

}
