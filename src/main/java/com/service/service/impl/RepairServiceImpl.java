package com.service.service.impl;

import com.service.dto.account.CommentCreate;
import com.service.dto.account.FullInfoRepair;
import com.service.dto.account.RepairCreate;
import com.service.dto.account.RepairStatus;
import com.service.dto.account.RepairUpdate;
import com.service.dto.account.ShortInfoRepair;
import com.service.entity.AccountEntity;
import com.service.entity.CommentEntity;
import com.service.entity.RepairEntity;
import com.service.exception.ServiceException;
import com.service.mapper.CommentMapper;
import com.service.mapper.RepairMapper;
import com.service.repository.ClientRepository;
import com.service.repository.CommentRepository;
import com.service.repository.RepairRepository;
import com.service.security.Session;
import com.service.service.AccountService;
import com.service.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.service.other.criteria.RepairUtils.findByValue;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final RepairMapper repairMapper;
    private final RepairRepository repairRepository;
    private final Session session;
    private final ClientRepository clientRepository;
    private final AccountService accountService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    private static final ServiceException REPAIR_EXCEPTION = new ServiceException("Repair entity not found");

    @Override
    public void createRepair(RepairCreate repairCreate) {
        RepairEntity repairEntity = repairMapper.map(repairCreate);
        repairEntity.setCreatedBy(session.getAccountSession());

        clientRepository.findById(repairCreate.getClientId())
                .ifPresent(repairEntity::setClient);

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

    @Override
    public List<ShortInfoRepair> findAllByClient(Long value) {
        return repairRepository.findAllRepairByClientId(value)
                .stream()
                .map(repairMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public FullInfoRepair findById(Long id) {
        return repairRepository.findById(id)
                .map(repairMapper::mapToFullInfoRepair)
                .orElseThrow(() -> REPAIR_EXCEPTION);
    }

    @Override
    public void updateDescription(RepairUpdate repairUpdate) {
        RepairEntity repairEntity = findRepair(repairUpdate);
        validateUpdateDescription(repairUpdate, repairEntity);

        repairEntity.setFullDescription(repairUpdate.getFullDescription());

        repairRepository.save(repairEntity);
    }

    @Override
    public void updateExecutor(RepairUpdate repairUpdate) {
        RepairEntity repairEntity = findRepair(repairUpdate);
        validateUpdateExecutor(repairUpdate, repairEntity);


        AccountEntity accountEntity = Optional.of(repairUpdate)
                .map(RepairUpdate::getExecutor)
                .map(accountService::findById)
                .orElse(null);

        repairEntity.setExecutor(accountEntity);

        repairRepository.save(repairEntity);
    }

    @Override
    public void updateStatus(RepairUpdate repairUpdate) {
        RepairEntity repairEntity = findRepair(repairUpdate);
        validateUpdateStatus(repairUpdate, repairEntity);

        repairEntity.setStatus(repairUpdate.getRepairStatus());

        if (repairUpdate.getRepairStatus().isFinal()) {
            repairEntity.setCompletionDate(LocalDateTime.now());
        }

        repairRepository.save(repairEntity);
    }

    @Override
    public void updatePrice(RepairUpdate repairUpdate) {
        RepairEntity repairEntity = findRepair(repairUpdate);
        validateUpdatePrice(repairUpdate, repairEntity);

        repairEntity.setPrice(repairUpdate.getPrice());

        repairRepository.save(repairEntity);
    }

    @Override
    public void createComment(CommentCreate commentCreate) {
        RepairEntity repairEntity = findRepair(commentCreate);
        CommentEntity commentEntity = commentMapper.map(commentCreate, repairEntity);
        commentEntity.setCreateBy(session.getAccountSession());
        commentRepository.save(commentEntity);
    }

    private RepairEntity findRepair(RepairUpdate repair) {
        return findRepair(repair.getId());
    }

    private RepairEntity findRepair(CommentCreate commentCreate) {
        return findRepair(commentCreate.getRepairId());
    }

    private RepairEntity findRepair(Long id) {
        return repairRepository.findById(id)
                .orElseThrow(() -> REPAIR_EXCEPTION);
    }


    private void validateUpdatePrice(RepairUpdate update, RepairEntity entity) {
        validateRepair(entity);

        if (!RepairStatus.ESTIMATION.equals(entity.getStatus())) {
            throw new ServiceException("Изменение стоимости ремонта возможно при статусе \"Оценка стоимости ремонта\"");
        }
    }

    private void validateUpdateDescription(RepairUpdate update, RepairEntity entity) {
        validateRepair(entity);
    }


    private void validateUpdateStatus(RepairUpdate update, RepairEntity entity) {
        validateRepair(entity);

        RepairStatus newStatus = update.getRepairStatus();

        switch (entity.getStatus()) {
            case ESTIMATION -> {
                if (containsConstant(RepairStatus.NEW, RepairStatus.DIAGNOSTICS, newStatus)) {
                    throw new ServiceException("Incorrect status");
                } else if (!newStatus.isFinal() && Objects.isNull(entity.getPrice())) {
                    throw new ServiceException("Для изменения статуса необходимо указать сумму ремонта");
                }

            }
            case APPROVAL -> {
                if (containsConstant(RepairStatus.NEW, RepairStatus.ESTIMATION, RepairStatus.DIAGNOSTICS, newStatus)) {
                    throw new ServiceException("Incorrect status");
                } else if (!newStatus.isFinal() && Objects.isNull(entity.getPrice())) {
                    throw new ServiceException("Для изменения статуса необходимо указать сумму ремонта");
                }

            }
            case DIAGNOSTICS -> {
                if (containsConstant(RepairStatus.NEW, newStatus)) {
                    throw new ServiceException("Incorrect status");
                }
            }
            case IN_PROGRESS -> {
                if (containsConstant(RepairStatus.NEW, RepairStatus.APPROVAL, RepairStatus.ESTIMATION, RepairStatus.DIAGNOSTICS, newStatus)) {
                    throw new ServiceException("Incorrect status");
                } else if (!newStatus.isFinal() && Objects.isNull(entity.getPrice())) {
                    throw new ServiceException("Для изменения статуса необходимо указать сумму ремонта");
                }
            }
        }
    }

    private void validateUpdateExecutor(RepairUpdate update, RepairEntity entity) {
        validateRepair(entity);
    }

    private void validateRepair(RepairEntity entity) {
        if (entity.getStatus().isFinal()) {
            throw new ServiceException("Запрещено менять объект при финальном статусе");
        }
    }

    private boolean containsConstant(RepairStatus status, RepairStatus... constants) {
        return Arrays.asList(constants).contains(status);
    }

}