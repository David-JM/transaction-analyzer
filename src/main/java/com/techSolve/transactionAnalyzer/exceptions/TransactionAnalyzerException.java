package com.techSolve.transactionAnalyzer.exceptions;

public class TransactionAnalyzerException extends RuntimeException {

    public TransactionAnalyzerException(String message) {
        super(message);
    }

    public TransactionAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }

}