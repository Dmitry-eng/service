package com.service.other.criteria;

import com.service.entity.AbstractEntity;
import com.service.entity.RepairEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class RepairUtils {

    private String CHAR = "%";

    public static Specification<RepairEntity> findByValue(String value) {
        return (repair, cq, cb) -> {
            Predicate predicate = cb.like(repair.get(RepairEntity.Fields.shortDescription), getValue(value));
            Predicate predicateId = null;
            if (StringUtils.isNumeric(value)) {
                predicateId = cb.equal(repair.get(AbstractEntity.Fields.id), value);
            }
            return Objects.nonNull(predicateId) ? cb.or(predicate, predicateId) : cb.or(predicate);
        };
    }

    private static String getValue(String value) {
        return CHAR + value + CHAR;
    }

}
