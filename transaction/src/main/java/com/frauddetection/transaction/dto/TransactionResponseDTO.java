package com.frauddetection.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.frauddetection.transaction.model.TransactionCurrency;
import com.frauddetection.transaction.model.TransactionStatus;
import com.frauddetection.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private BigDecimal amount;
    private TransactionCurrency currency;
    private TransactionType type;
    private String senderCardNumber;
    private String receiverCardNumber;
    private TransactionStatus status;
    private LocalDateTime createdAt;
}
