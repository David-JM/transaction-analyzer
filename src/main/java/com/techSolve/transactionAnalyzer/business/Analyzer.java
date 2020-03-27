package com.techSolve.transactionAnalyzer.business;

import com.techSolve.transactionAnalyzer.business.model.Transaction;
import com.techSolve.transactionAnalyzer.exceptions.TransactionAnalyzerException;

import java.time.Month;
import java.util.Comparator;
import java.util.List;

public class Analyzer {

    private static final String POSITIVO = "Positivo";
    private static final String NEGATIVO = "Negativo";

    public Integer generateTotalAmount(final List<Transaction> transactions) {
        return transactions.stream().mapToInt(Transaction::getValue).sum();
    }

    public String getIfAmountIsPositiveOrNegative(final Integer totalAmount) {
        return (totalAmount >= 0) ? POSITIVO : NEGATIVO;
    }

    public Transaction extractMinValue(final List<Transaction> transactions) {
        return transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .orElseThrow(() -> new TransactionAnalyzerException("NO SE ENCONTRÓ NINGÚN VALOR"));
    }

    public Integer generateTotalAmountByMonth(final List<Transaction> transactions, final Month month) {
        return transactions.stream().
                filter(transaction -> transaction.getDate().getMonth().equals(month))
                .mapToInt(Transaction::getValue).sum();
    }
}
