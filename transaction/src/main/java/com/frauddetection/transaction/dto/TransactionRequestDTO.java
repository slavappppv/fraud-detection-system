package com.frauddetection.transaction.dto;

import java.math.BigDecimal;

import com.frauddetection.transaction.model.TransactionCurrency;
import com.frauddetection.transaction.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRequestDTO {
    @Positive
    private BigDecimal amount;
    @NotNull
    private TransactionCurrency currency;
    @NotNull
    private TransactionType type;
    @NotBlank
    @Pattern(regexp = "\\d{16}", message = "Card number must contain 16 digits")
    private String senderCardNumber;
    @NotBlank
    @Pattern(regexp = "\\d{16}", message = "Card number must contain 16 digits")
    private String receiverCardNumber;
}

