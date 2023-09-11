package com.service.repository;

import com.service.entity.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>, JpaSpecificationExecutor<RepairEntity> {

    List<RepairEntity> findAllRepairByClientId(Long id);

}
