package demo.transactionAnalyzer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class Transaction {

    private LocalDate date;
    private Integer value;
    private String category;

}
