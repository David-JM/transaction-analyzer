package com.techSolve.transactionAnalyzer.business;

import com.techSolve.transactionAnalyzer.business.model.Transaction;
import com.techSolve.transactionAnalyzer.utility.DataFormatter;
import com.techSolve.transactionAnalyzer.commons.DateFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AnalyzerTest {

    private static final String DATE_01_01_2019 = "01-01-2019";
    private static final String DATE_03_02_2019 = "03-02-2019";
    private static final String DATE_15_02_2019 = "15-02-2019";

    private static final Integer FIFTY_THOUSAND = 50000;
    private static final Integer ONE_BILLION = 1000000;
    private static final Integer MINUS_TWENTY_THOUSAND = -20000;
    private static final Integer MINUS_ONE_HUNDRED_TWENTY_THOUSAND = -120000;
    private static final Integer MINUS_SEVENTY_THOUSAND = -70000;

    private static final String CATEGORY_RENTA = "Renta";
    private static final String CATEGORY_SERVICIOS = "Servicios";
    private static final String CATEGORY_INTERNET = "Internet";
    private static final String CATEGORY_MERCADO = "Mercado";
    private static final String CATEGORY_SALARIO = "Salario";

    private static final String POSITIVO = "Positivo";
    private static final String NEGATIVO = "Negativo";

    private Analyzer analyzer;
    private List<Transaction> transactions;

    public AnalyzerTest(){
        analyzer = new Analyzer();
        transactions = new ArrayList<>();
    }

    @After
    public void clearTransactions() {
        transactions.clear();
    }

    @Test
    public void shouldGenerateTotalAmountThirtyThousand() {
        //Arrange
        final Integer amountExpected = 30000;

        final LocalDate date = DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, DATE_03_02_2019);
        transactions.add(new Transaction(date, FIFTY_THOUSAND, CATEGORY_RENTA));
        transactions.add(new Transaction(date, MINUS_TWENTY_THOUSAND, CATEGORY_SERVICIOS));

        //Act
        final Integer totalAmount = analyzer.generateTotalAmount(transactions);

        //Assert
        Assert.assertEquals(amountExpected, totalAmount);
    }

    @Test
    public void shouldGenerateTotalAmountMinusOneHundredFortyThousand() {
        final Integer amountExpected = -140000;

        final LocalDate date = DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, DATE_15_02_2019);
        transactions.add(new Transaction(date, FIFTY_THOUSAND, CATEGORY_RENTA));
        transactions.add(new Transaction(date, MINUS_ONE_HUNDRED_TWENTY_THOUSAND, CATEGORY_INTERNET));
        transactions.add(new Transaction(date, MINUS_SEVENTY_THOUSAND, CATEGORY_MERCADO));

        final Integer totalAmount = analyzer.generateTotalAmount(transactions);

        Assert.assertEquals(amountExpected, totalAmount);
    }

    @Test
    public void shouldValidateTotalAmountPositive() {
        final Integer totalAmount = 2345000;

        final String sign = analyzer.getIfAmountIsPositiveOrNegative(totalAmount);

        Assert.assertEquals(POSITIVO, sign);
    }

    @Test
    public void shouldValidateTotalAmountNegative() {
        final Integer totalAmount = -345000;

        final String sign = analyzer.getIfAmountIsPositiveOrNegative(totalAmount);

        Assert.assertEquals(NEGATIVO, sign);
    }

    @Test
    public void shouldExtractNegativeValueOneHundredTwentyThousand() {
        final Integer minValueExpected = -120000;

        final LocalDate date = DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, DATE_01_01_2019);
        transactions.add(new Transaction(date, MINUS_TWENTY_THOUSAND, CATEGORY_RENTA));
        transactions.add(new Transaction(date, MINUS_SEVENTY_THOUSAND, CATEGORY_MERCADO));
        transactions.add(new Transaction(date, MINUS_ONE_HUNDRED_TWENTY_THOUSAND, CATEGORY_INTERNET));

        final Transaction transaction = analyzer.extractMinValue(transactions);

        Assert.assertEquals(minValueExpected, transaction.getValue());
    }

    @Test
    public void shouldGenerateJanuaryTotalAmount() {
        final Integer januaryAmountExpected = 1030000;

        final LocalDate date = DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, DATE_01_01_2019);
        transactions.add(new Transaction(date, FIFTY_THOUSAND, CATEGORY_RENTA));
        transactions.add(new Transaction(date, MINUS_TWENTY_THOUSAND, CATEGORY_SERVICIOS));
        transactions.add(new Transaction(date, ONE_BILLION, CATEGORY_SALARIO));

        final Integer januaryAmount = analyzer.generateTotalAmountByMonth(transactions, Month.JANUARY);

        Assert.assertEquals(januaryAmountExpected, januaryAmount);
    }

}
