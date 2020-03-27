package com.techSolve.transactionAnalyzer.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_readers")
public class Reader {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "code_type")
    private String codeType;

    @Column(name = "file")
    private String file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}