package com.techSolve.transactionAnalyzer.controller;

import org.junit.Assert;
import org.junit.Test;

public class TransactionAnalizerTest {

    /*@Test
    public void debeTotalizarElMontoDelArchivo(){
        TransactionAnalizer transactionAnalizer = new TransactionAnalizer();
        String filePath = "src/test/resources/transactions.csv";
        int result = transactionAnalizer.execute(filePath);
        Assert.assertTrue(1275000 == result);
    }

    @Test
    public void debeValidarMontoPositivo(){
        TransactionAnalizer  transactionAnalizer = new TransactionAnalizer();
        int monto = 2345000;
        boolean positivo = transactionAnalizer.validateSignAmount(monto);
        Assert.assertTrue(positivo);
    }

    @Test
    public void debeValidarMontoNegativo(){
        TransactionAnalizer transactionAnalizer = new TransactionAnalizer();
        int monto = -345000;
        boolean negativo = transactionAnalizer.validateSignAmount(monto);
        Assert.assertFalse(negativo);
    }

    @Test
    public void debeValidarCategoriaMasGasta(){
        TransactionAnalizer transactionAnalizer = new TransactionAnalizer();
        String filePath = "src/test/resources/transactions.csv";
        String category = transactionAnalizer.getCategoryMoreExpensive(filePath);
        Assert.assertEquals("Mercado",category);
    }*/
}
