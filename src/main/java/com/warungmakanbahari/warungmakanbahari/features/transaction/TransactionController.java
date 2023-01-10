package com.warungmakanbahari.warungmakanbahari.features.transaction;

import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.AddTransactionRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.TransactionDto;
import com.warungmakanbahari.warungmakanbahari.shared.constants.UrlMapping;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.CommonResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.TRANSACTION_URL)
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> addTransaction(@RequestBody AddTransactionRequestDto dto) {
        TransactionDto response = transactionService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.CREATED),
                "Success Add Transaction",
                response
        ));
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        PagedResponse<TransactionDto> response;

        response = transactionService.getAll(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get All Transaction",
                response
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable("id") Long id) {
        TransactionDto response = transactionService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get Transaction",
                response
        ));
    }
}
