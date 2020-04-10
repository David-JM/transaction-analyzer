package demo.transactionAnalyzer.utility.readers.factory;

import demo.transactionAnalyzer.domain.Transaction;
import demo.transactionAnalyzer.exceptions.TransactionAnalyzerException;

import java.util.List;

public interface IReader {

    List<String> readFile() throws TransactionAnalyzerException;

    List<Transaction> getTransactionsFromFile(final List<String> fileLines);

}