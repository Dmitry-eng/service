package com.service.service;

import com.service.dto.comment.CommentCreate;
import com.service.dto.repair.FullInfoRepair;
import com.service.dto.repair.RepairCreate;
import com.service.dto.repair.RepairUpdate;
import com.service.dto.repair.ShortInfoRepair;

import java.util.List;

public interface RepairService {
    void createRepair(RepairCreate repairCreate);
    List<ShortInfoRepair> findAllRepair(String value);
    List<ShortInfoRepair> findAllByClient(Long value);
    FullInfoRepair findById(Long id);
    void updateDescription(RepairUpdate repairUpdate);
    void updateExecutor(RepairUpdate repairUpdate);
    void updateStatus(RepairUpdate repairUpdate);
    void updatePrice(RepairUpdate repairUpdate);
    void createComment(CommentCreate commentCreate);
}
