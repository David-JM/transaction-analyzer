package demo.transactionAnalyzer.utility.readers;

import demo.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReaderCSVTest {

    @InjectMocks
    private ReaderCSV readerCSV;

    @Test
    public void shouldValidateFileWithContent() {
        final List<String> transactions = readerCSV.readFile();
        Assertions.assertTrue(null != transactions);
    }

    @Test
    public void shouldReadFileLineToGenerateTransactionWithFiftyThousand() {
        final String FILE_LINE_1 = "01-01-2019,50000,Renta";
        final int expectedValue = 50000;

        int actualValue = readerCSV.getTransactionsFromFile(Arrays.asList(FILE_LINE_1)).stream().findFirst().get().getValue();

        Assertions.assertEquals(expectedValue, actualValue);
    }

}
