package com.techSolve.transactionAnalyzer.controller;

import com.techSolve.transactionAnalyzer.business.Analyzer;
import com.techSolve.transactionAnalyzer.business.model.Transaction;
import com.techSolve.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import com.techSolve.transactionAnalyzer.repository.IReaderTypeRepository;
import com.techSolve.transactionAnalyzer.utility.factory.IReader;
import com.techSolve.transactionAnalyzer.utility.factory.ReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/reader")
public class TransactionAnalizer {

    private Analyzer analyzer;
    private List<Transaction> transactions = null;

    @Autowired
    private ReaderFactory readerFactory;

    @Autowired
    private IReaderTypeRepository readerTypeRepository;

    private IReader reader;

    public TransactionAnalizer() {
        analyzer = new Analyzer();
    }

    @GetMapping(value = "/{type}")
    public void setTypeFile(@PathVariable String type) {
        final String filePath = readerTypeRepository.findByCodeType(type)
                .orElseThrow(() -> new TransactionAnalyzerException("NO SE ENCONTRO ESE TIPO DE READER")).getFile();

        reader = readerFactory.getInstance(type);

        final List<String> fileLines = reader.readFile(filePath);
        transactions = reader.getTransactionsFromFile(fileLines);
    }

    @GetMapping(value = "/totalAmount")
    public String getTotalAmountFromFile() {
        return "El monto total del archivo es:" + analyzer.generateTotalAmount(transactions);
    }

    @GetMapping(value = "/signAmount")
    public String getAmountSign() {
        final Integer totalAmount = analyzer.generateTotalAmount(transactions);
        return "El monto del archivo es:" + analyzer.getIfAmountIsPositiveOrNegative(totalAmount);
    }

    @GetMapping(value = "/categoryExpensive")
    public String getCategoryMoreExpensive() {
        return "La categoria en la que mas gasto fue: " + analyzer.extractMinValue(transactions).getCategory();
    }

    @GetMapping(value = "/amountByMonth")
    public String getTotalAmountByMonth() {
        return "El monto total de enero es: " + analyzer.generateTotalAmountByMonth(transactions, Month.JANUARY);
    }
}
