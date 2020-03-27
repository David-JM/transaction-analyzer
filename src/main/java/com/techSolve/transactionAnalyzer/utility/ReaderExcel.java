package com.techSolve.transactionAnalyzer.utility;

import com.techSolve.transactionAnalyzer.business.model.Transaction;
import com.techSolve.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import com.techSolve.transactionAnalyzer.utility.factory.IReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderExcel implements IReader {
    @Override
    public List<String> readFile(String filePath) throws TransactionAnalyzerException {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsFromFile(List<String> fileLines) {
        return null;
    }

}
