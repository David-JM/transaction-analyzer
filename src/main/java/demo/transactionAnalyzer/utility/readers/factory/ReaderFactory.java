package demo.transactionAnalyzer.utility.readers.factory;

import demo.transactionAnalyzer.commons.ReaderType;
import demo.transactionAnalyzer.utility.readers.ReaderCSV;
import demo.transactionAnalyzer.utility.readers.ReaderExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReaderFactory {
    private Map<String, IReader> map;

    private final ReaderCSV readerCSV;
    private final ReaderExcel readerExcel;

    @Autowired
    public ReaderFactory(ReaderCSV readerCSV, ReaderExcel readerExcel) {
        this.readerCSV = readerCSV;
        this.readerExcel = readerExcel;
        init();
    }

    public IReader getInstance(String type) {
        return map.get(type);
    }

    private void init() {
        map = new HashMap<>();
        map.put(ReaderType.CSV.getType(), readerCSV);
        map.put(ReaderType.XLSX.getType(), readerExcel);
    }
}