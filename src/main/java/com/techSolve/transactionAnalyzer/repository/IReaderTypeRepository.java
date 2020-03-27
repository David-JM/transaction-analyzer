package com.techSolve.transactionAnalyzer.repository;

import com.techSolve.transactionAnalyzer.entity.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReaderTypeRepository extends CrudRepository<Reader, Integer> {

    public Optional<Reader> findByCodeType(String codeType);
}