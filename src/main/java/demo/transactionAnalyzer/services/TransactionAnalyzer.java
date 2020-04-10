package demo.transactionAnalyzer.services;

import demo.transactionAnalyzer.domain.Transaction;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Comparator;
import java.util.List;

@Service
public class TransactionAnalyzer {

    private static final String POSITIVO = "Positivo";
    private static final String NEGATIVO = "Negativo";

    public int generateTotalAmount(final List<Transaction> transactions) {
        return transactions.stream().mapToInt(Transaction::getValue).sum();
    }

    public String getAmountSign(final Integer totalAmount) {
        return (totalAmount >= 0) ? POSITIVO : NEGATIVO;
    }

    public String extractCategoryWithLowestValue(final List<Transaction> transactions) {
        return transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get().getCategory();
    }

    public int generateTotalAmountByMonth(final List<Transaction> transactions, final Month month) {
        return transactions.stream().
                filter(transaction -> transaction.getDate().getMonth().equals(month))
                .mapToInt(Transaction::getValue).sum();
    }
}
