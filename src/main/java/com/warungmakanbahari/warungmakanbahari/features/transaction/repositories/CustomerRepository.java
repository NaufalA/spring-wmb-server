package com.warungmakanbahari.warungmakanbahari.features.transaction.repositories;

import com.warungmakanbahari.warungmakanbahari.features.transaction.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByName(String name);
}
