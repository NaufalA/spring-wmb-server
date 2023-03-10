package com.warungmakanbahari.warungmakanbahari.features.transaction.repositories;

import com.warungmakanbahari.warungmakanbahari.features.transaction.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>, JpaSpecificationExecutor<TransactionEntity> {
}
