package com.frauddetection.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    private TransactionCurrency currency;
    private TransactionType type;
    private String senderCardNumber;
    private String receiverCardNumber;
    private TransactionStatus status;
    private String ipAddress;
    private LocalDateTime createdAt;
}
