package com.service.service.impl;

import com.service.dto.account.RepairCreate;
import com.service.dto.account.ShortInfoRepair;
import com.service.entity.RepairEntity;
import com.service.mapper.RepairMapper;
import com.service.repository.RepairRepository;
import com.service.service.RepairService;
import com.service.utils.criteria.RepairUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.service.utils.criteria.RepairUtils.*;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final RepairMapper repairMapper;
    private final RepairRepository repairRepository;

    @Override
    public void createRepair(RepairCreate repairCreate) {
        RepairEntity repairEntity = repairMapper.map(repairCreate);
        repairRepository.save(repairEntity);
    }

    @Override
    public List<ShortInfoRepair> findAllRepair(String value) {
        List<RepairEntity> repairEntities = null;

        if (StringUtils.isEmpty(value)) {
            repairEntities = repairRepository.findAll();
        } else {
            repairEntities = repairRepository.findAll(findByValue(value));
        }

        return repairEntities.stream()
                .map(repairMapper::map)
                .collect(Collectors.toList());
    }

}
