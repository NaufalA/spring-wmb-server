package com.warungmakanbahari.warungmakanbahari.features.table.repositories;

import com.warungmakanbahari.warungmakanbahari.features.table.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TableRepository extends JpaRepository<TableEntity, Long>, JpaSpecificationExecutor<TableEntity> {
}
