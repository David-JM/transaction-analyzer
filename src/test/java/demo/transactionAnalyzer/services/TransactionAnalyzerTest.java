package demo.transactionAnalyzer.services;

import demo.transactionAnalyzer.commons.DateFormat;
import demo.transactionAnalyzer.domain.Transaction;
import demo.transactionAnalyzer.utility.DataFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TransactionAnalyzerTest {

    private static final String DATE_01_01_2019 = "01-01-2019";

    private static final Integer ONE_BILLION = 1000000;
    private static final Integer FIFTY_THOUSAND = 50000;
    private static final Integer MINUS_TWENTY_THOUSAND = -20000;
    private static final Integer MINUS_SEVENTY_THOUSAND = -70000;
    private static final Integer MINUS_ONE_HUNDRED_TWENTY_THOUSAND = -120000;

    private static final String RENTA = "Renta";
    private static final String INTERNET = "Internet";
    private static final String MERCADO = "Mercado";

    private TransactionAnalyzer transactionAnalyzer;

    public TransactionAnalyzerTest() {
        transactionAnalyzer = new TransactionAnalyzer();
    }

    @ParameterizedTest
    @MethodSource("provideTransactions")
    public void shouldGenerateTotalAmount(List<Transaction> transactions, int expectedAmount) {
        final int actualAmount = transactionAnalyzer.generateTotalAmount(transactions);
        Assertions.assertEquals(expectedAmount, actualAmount);
    }

    private static Stream<Arguments> provideTransactions() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        Transaction.builder().value(FIFTY_THOUSAND).build(),
                        Transaction.builder().value(MINUS_TWENTY_THOUSAND).build()
                ), 30000),
                Arguments.of(Arrays.asList(
                        Transaction.builder().value(FIFTY_THOUSAND).build(),
                        Transaction.builder().value(MINUS_ONE_HUNDRED_TWENTY_THOUSAND).build(),
                        Transaction.builder().value(MINUS_SEVENTY_THOUSAND).build()
                ), -140000)
        );
    }

    @ParameterizedTest
    @CsvSource({"2345000,Positivo", "-345000,Negativo"})
    public void shouldGetAmountSign(int amount, String expectedSign) {
        final String actualSign = transactionAnalyzer.getAmountSign(amount);
        Assertions.assertEquals(expectedSign, actualSign);
    }

    @Test
    public void shouldExtractLowestValueInternetCategory() {
        final List<Transaction> transactions = Arrays.asList(
                Transaction.builder().value(MINUS_TWENTY_THOUSAND).category(RENTA).build(),
                Transaction.builder().value(MINUS_SEVENTY_THOUSAND).category(MERCADO).build(),
                Transaction.builder().value(MINUS_ONE_HUNDRED_TWENTY_THOUSAND).category(INTERNET).build()
        );

        final String actualCategory = transactionAnalyzer.extractCategoryWithLowestValue(transactions);

        Assertions.assertEquals(INTERNET, actualCategory);
    }

    @Test
    public void shouldGenerateJanuaryTotalAmount() {
        final Integer expectedJanuaryAmount = 1030000;

        final LocalDate date = DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, DATE_01_01_2019);

        final List<Transaction> transactions = Arrays.asList(
                Transaction.builder().date(date).value(FIFTY_THOUSAND).build(),
                Transaction.builder().date(date).value(MINUS_TWENTY_THOUSAND).build(),
                Transaction.builder().date(date).value(ONE_BILLION).build()
        );

        final Integer actualJanuaryAmount = transactionAnalyzer.generateTotalAmountByMonth(transactions, Month.JANUARY);

        Assertions.assertEquals(expectedJanuaryAmount, actualJanuaryAmount);
    }

}
