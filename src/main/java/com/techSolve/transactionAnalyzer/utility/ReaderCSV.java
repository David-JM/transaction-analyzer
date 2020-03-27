package com.techSolve.transactionAnalyzer.utility;

import com.techSolve.transactionAnalyzer.business.model.Transaction;
import com.techSolve.transactionAnalyzer.commons.DateFormat;
import com.techSolve.transactionAnalyzer.commons.ReaderType;
import com.techSolve.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import com.techSolve.transactionAnalyzer.repository.IReaderConfigurationRepository;
import com.techSolve.transactionAnalyzer.utility.factory.IReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderCSV implements IReader {

    private static final String SPLIT_CHARACTER = "splitCharacter";
    private static final String DATE_INDEX = "dateIndex";
    private static final String VALUE_INDEX = "valueIndex";
    private static final String CATEGORY_INDEX = "categoryIndex";

    @Autowired
    private IReaderConfigurationRepository readerConfiguration;

    @Override
    public List<String> readFile(final String filePath) throws TransactionAnalyzerException {
        try {
            final Path path = Paths.get(filePath);
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new TransactionAnalyzerException("NO SE PUDO LEER EL ARCHIVO POR: " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Transaction> getTransactionsFromFile(final List<String> fileLines) {
        return fileLines.stream()
                .map((line) -> {
                    final String[] data = line.split(getConfiguration(SPLIT_CHARACTER));

                    final Integer dateIndex = Integer.parseInt(getConfiguration(DATE_INDEX));
                    final LocalDate date = DataFormatter.generateDateWithFormat(DateFormat.DD_MM_YYYY, data[dateIndex]);

                    final Integer valueIndex = Integer.parseInt(getConfiguration(VALUE_INDEX));
                    final Integer value = Integer.parseInt(data[valueIndex]);

                    final Integer categoryIndex = Integer.parseInt(getConfiguration(CATEGORY_INDEX));
                    final String category = data[categoryIndex];

                    return new Transaction(date, value, category);
                })
                .collect(Collectors.toList());
    }

    private String getConfiguration(String code) {
        return readerConfiguration.findByReaderCodeTypeAndCode(ReaderType.CSV.getType(), code)
                .orElseThrow(() -> new TransactionAnalyzerException("No se encontro el " + code)).getContent();
    }
}
