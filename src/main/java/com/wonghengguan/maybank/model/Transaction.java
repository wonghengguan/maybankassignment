package com.wonghengguan.maybank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transaction implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private BigDecimal trxAmount;

    @Temporal(TemporalType.DATE)
    private Date trxDate;

    private Time trxTime;

    private String description;

    private Long accountNumber;

    private Long customerId;
}
