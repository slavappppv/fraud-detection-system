package com.frauddetection.transaction.service;

import com.frauddetection.transaction.dto.TransactionRequestDTO;
import com.frauddetection.transaction.dto.TransactionResponseDTO;
import com.frauddetection.transaction.model.Transaction;
import com.frauddetection.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.frauddetection.transaction.model.TransactionStatus.CREATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionResponseDTO create(TransactionRequestDTO transactionUser, String ipAddress) {
        log.info("Creating transaction from card: {}", transactionUser.getSenderCardNumber());
        Transaction transaction = Transaction.builder()
                .amount(transactionUser.getAmount())
                .currency(transactionUser.getCurrency())
                .type(transactionUser.getType())
                .senderCardNumber(transactionUser.getSenderCardNumber())
                .receiverCardNumber(transactionUser.getReceiverCardNumber())
                .status(CREATED)
                .createdAt(LocalDateTime.now())
                .ipAddress(ipAddress)
                .build();
        transaction = transactionRepository.save(transaction);

        TransactionResponseDTO transactionResponse = TransactionResponseDTO.builder().
                id(transaction.getId()).
                amount(transaction.getAmount()).
                currency(transaction.getCurrency()).
                type(transaction.getType()).
                senderCardNumber(transaction.getSenderCardNumber()).
                receiverCardNumber(transaction.getReceiverCardNumber()).
                status(transaction.getStatus()).
                createdAt(transaction.getCreatedAt()).build();

        log.info("Transaction created with id: {}", transaction.getId());
        return transactionResponse;
    }

    public TransactionResponseDTO findById(Long id) {
        log.debug("Finding transaction by id: {}", id);
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found: " + id));
        TransactionResponseDTO transactionResponse = TransactionResponseDTO.builder().
                id(transaction.getId()).
                amount(transaction.getAmount()).
                currency(transaction.getCurrency()).
                type(transaction.getType()).
                senderCardNumber(transaction.getSenderCardNumber()).
                receiverCardNumber(transaction.getReceiverCardNumber()).
                status(transaction.getStatus()).
                createdAt(transaction.getCreatedAt()).build();
        log.info("Transaction get with id: {}", transaction.getId());
        return transactionResponse;
    }
}
