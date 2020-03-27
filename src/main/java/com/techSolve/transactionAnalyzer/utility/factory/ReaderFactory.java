package com.techSolve.transactionAnalyzer.utility.factory;

import com.techSolve.transactionAnalyzer.commons.ReaderType;
import com.techSolve.transactionAnalyzer.utility.ReaderCSV;
import com.techSolve.transactionAnalyzer.utility.ReaderExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReaderFactory {

    private Map<String, IReader> map;

    @Autowired
    private ReaderCSV readerCSV;

    @Autowired
    private ReaderExcel readerExcel;

    public IReader getInstance(String type) {
        init();
        return map.get(type);
    }

    private void init() {
        map = new HashMap<String, IReader>();
        map.put(ReaderType.CSV.getType(), readerCSV);
        map.put(ReaderType.XLSX.getType(), readerExcel);
    }
}