package demo.transactionAnalyzer.utility.readers;

import demo.transactionAnalyzer.commons.DateFormat;
import demo.transactionAnalyzer.domain.Transaction;
import demo.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import demo.transactionAnalyzer.utility.DataFormatter;
import demo.transactionAnalyzer.utility.readers.factory.IReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderCSV implements IReader {

    private static final String FILE_PATH = "src/test/resources/transactions.csv";

    private static final String SPLIT_CHARACTER = ",";
    private static final int DATE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int CATEGORY_INDEX = 2;

    private static final String NO_SE_PUDO_LEER_EL_ARCHIVO = "NO SE PUDO LEER EL ARCHIVO POR: ";

    @Override
    public List<String> readFile() throws TransactionAnalyzerException {
        try {
            final Path path = Paths.get(FILE_PATH);
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new TransactionAnalyzerException(NO_SE_PUDO_LEER_EL_ARCHIVO + e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Transaction> getTransactionsFromFile(final List<String> fileLines) {
        return fileLines.stream()
                .map((line) -> {
                    final String[] data = line.split(SPLIT_CHARACTER);
                    return Transaction.builder()
                            .date(DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, data[DATE_INDEX]))
                            .value(Integer.parseInt(data[VALUE_INDEX]))
                            .category(data[CATEGORY_INDEX])
                            .build();
                })
                .collect(Collectors.toList());
    }
}
