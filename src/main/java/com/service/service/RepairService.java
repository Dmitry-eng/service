package com.service.service;

import com.service.dto.account.RepairCreate;
import com.service.dto.account.ShortInfoRepair;

import java.util.List;

public interface RepairService {
    void createRepair(RepairCreate repairCreate);
    List<ShortInfoRepair> findAllRepair(String value);
}
