package com.techSolve.transactionAnalyzer.repository;

import com.techSolve.transactionAnalyzer.entity.ReaderConfiguration;
import com.techSolve.transactionAnalyzer.entity.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReaderConfigurationRepository extends CrudRepository<ReaderConfiguration, Integer> {

    public Optional<ReaderConfiguration> findByReaderCodeTypeAndCode(String type, String code);
}