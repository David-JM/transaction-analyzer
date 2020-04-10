package demo.transactionAnalyzer.controller;

import demo.transactionAnalyzer.domain.Transaction;
import demo.transactionAnalyzer.services.TransactionAnalyzer;
import demo.transactionAnalyzer.utility.readers.factory.IReader;
import demo.transactionAnalyzer.utility.readers.factory.ReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private IReader reader;

    private final TransactionAnalyzer transactionAnalyzer;
    private final ReaderFactory readerFactory;

    @Autowired
    public TransactionController(TransactionAnalyzer transactionAnalyzer, ReaderFactory readerFactory) {
        this.transactionAnalyzer = transactionAnalyzer;
        this.readerFactory = readerFactory;
    }

    @GetMapping(value = "/{readerType}")
    public List<Transaction> getTransactions(@PathVariable String readerType) {
        reader = readerFactory.getInstance(readerType);
        return reader.getTransactionsFromFile(reader.readFile());
    }

    @GetMapping(value = "/totalAmount")
    public int getTotalAmountFromFile(@RequestParam String readerType) {
        return transactionAnalyzer.generateTotalAmount(getTransactions(readerType));
    }

    @GetMapping(value = "/signAmount")
    public String getAmountSign(@RequestParam String readerType) {
        return transactionAnalyzer.getAmountSign(getTotalAmountFromFile(readerType));
    }

    @GetMapping(value = "/categoryExpensive")
    public String getCategoryMoreExpensive(@RequestParam String readerType) {
        return transactionAnalyzer.extractCategoryWithLowestValue(getTransactions(readerType));
    }

    @GetMapping(value = "/amountByMonth")
    public int getTotalAmountByMonth(@RequestParam String readerType) {
        return transactionAnalyzer.generateTotalAmountByMonth(getTransactions(readerType), Month.JANUARY);
    }
}
