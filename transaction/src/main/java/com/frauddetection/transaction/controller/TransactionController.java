package com.frauddetection.transaction.controller;

import com.frauddetection.transaction.dto.TransactionRequestDTO;
import com.frauddetection.transaction.dto.TransactionResponseDTO;
import com.frauddetection.transaction.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@Valid @RequestBody TransactionRequestDTO requestDTO, HttpServletRequest request) {
       TransactionResponseDTO createdTransaction = service.create(requestDTO, request.getRemoteAddr()); //ip берется без учета прокси
        return ResponseEntity.status(HttpStatus.CREATED).
                header("Location", "/api/v1/transactions/" + createdTransaction.getId()).
                body(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> get(@PathVariable("id") Long id) {
        TransactionResponseDTO transaction = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(transaction);
    }
}
