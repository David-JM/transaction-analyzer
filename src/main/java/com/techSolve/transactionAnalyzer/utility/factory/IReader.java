package com.techSolve.transactionAnalyzer.utility.factory;

import com.techSolve.transactionAnalyzer.business.model.Transaction;
import com.techSolve.transactionAnalyzer.exceptions.TransactionAnalyzerException;

import java.util.List;

public interface IReader {

    public List<String> readFile(final String filePath) throws TransactionAnalyzerException;

    public List<Transaction> getTransactionsFromFile(final List<String> fileLines);

}