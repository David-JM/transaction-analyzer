package com.techSolve.transactionAnalyzer.utility;

import com.techSolve.transactionAnalyzer.commons.ReaderType;
import com.techSolve.transactionAnalyzer.entity.Reader;
import com.techSolve.transactionAnalyzer.entity.ReaderConfiguration;
import com.techSolve.transactionAnalyzer.exceptions.TransactionAnalyzerException;
import com.techSolve.transactionAnalyzer.repository.IReaderConfigurationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReaderCSVTest {

    private static final String FILE_PATH = "src/test/resources/transactions.csv";

    private static final String FILE_LINE_1 = "01-01-2019,50000,Renta";
    private static final Integer FIFTY_THOUSAND_EXPECTED = 50000;

    private static final String FILE_LINE_2 = "15-01-2019,-20000,Servicios";
    private static final Integer MINUS_TWENTY_THOUSAND_EXPECTED = -20000;

    @InjectMocks
    private ReaderCSV readerCSV;

    @Mock
    private IReaderConfigurationRepository readerConfigurationRepository;

    @Test
    public void shouldValidateFileWithContent() {
        final List<String> transactions = readerCSV.readFile(FILE_PATH);
        Assert.assertTrue(null != transactions);
    }

    @Test(expected = TransactionAnalyzerException.class)
    public void shouldValidateFileWithoutContent() {
        final List<String> transactions = readerCSV.readFile("");
    }

    @Test
    public void shouldExtractNumberValueFiftyThousand() {
        //Arrange
        final ReaderConfiguration readerConfigurationSplit = new ReaderConfiguration();
        readerConfigurationSplit.setContent(",");

        final ReaderConfiguration readerConfigurationDate = new ReaderConfiguration();
        readerConfigurationDate.setContent("0");

        final ReaderConfiguration readerConfigurationValue = new ReaderConfiguration();
        readerConfigurationValue.setContent("1");

        final ReaderConfiguration readerConfigurationCategory = new ReaderConfiguration();
        readerConfigurationCategory.setContent("2");

        //Act
        when(readerConfigurationRepository.findByReaderCodeTypeAndCode(ReaderType.CSV.getType(), "splitCharacter"))
                .thenReturn(Optional.of(readerConfigurationSplit));
        when(readerConfigurationRepository.findByReaderCodeTypeAndCode(ReaderType.CSV.getType(), "dateIndex"))
                .thenReturn(Optional.of(readerConfigurationDate));
        when(readerConfigurationRepository.findByReaderCodeTypeAndCode(ReaderType.CSV.getType(), "valueIndex"))
                .thenReturn(Optional.of(readerConfigurationValue));
        when(readerConfigurationRepository.findByReaderCodeTypeAndCode(ReaderType.CSV.getType(), "categoryIndex"))
                .thenReturn(Optional.of(readerConfigurationCategory));

        final Integer value = readerCSV.getTransactionsFromFile(Arrays.asList(FILE_LINE_1)).get(0).getValue();
        //verify(readerConfigurationRepository, times(0)).save(any(ReaderConfiguration.class));

        //Assert
        Assert.assertEquals(FIFTY_THOUSAND_EXPECTED, value);
    }

}
