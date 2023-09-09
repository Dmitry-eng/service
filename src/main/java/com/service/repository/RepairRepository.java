package com.service.repository;

import com.service.entity.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepairRepository extends JpaRepository<RepairEntity, Long>, JpaSpecificationExecutor<RepairEntity> {
}
