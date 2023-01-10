package com.warungmakanbahari.warungmakanbahari.features.transaction;

import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.AddTransactionRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.transaction.dtos.TransactionDto;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceCreate;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceGet;

public interface TransactionService extends ServiceCreate<TransactionDto, AddTransactionRequestDto>, ServiceGet<TransactionDto, Long> {
}
