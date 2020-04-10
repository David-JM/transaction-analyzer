package demo.transactionAnalyzer.utility.readers;

import demo.transactionAnalyzer.domain.Transaction;
import demo.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import demo.transactionAnalyzer.utility.readers.factory.IReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReaderExcel implements IReader {

    @Override
    public List<String> readFile() throws TransactionAnalyzerException {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsFromFile(List<String> fileLines) {
        return null;
    }

}
