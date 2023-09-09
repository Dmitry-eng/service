package com.service.utils.criteria;

import com.service.entity.AbstractEntity;
import com.service.entity.RepairEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.NumberUtils;

@UtilityClass
public class RepairUtils {

    private String CHAR = "%";

   public static Specification<RepairEntity> findByValue(String value) {
        return (repair, cq, cb) -> {
            Predicate id = cb.equal(repair.get(AbstractEntity.Fields.id), getValue(value));
            Predicate shortDescription = cb.equal(repair.get(RepairEntity.Fields.shortDescription), getValue(value));
            Predicate status = cb.equal(repair.get(RepairEntity.Fields.status), getValue(value));
            return StringUtils.isNumeric(value) ? cb.or(id, shortDescription, status) : cb.or(shortDescription, status);
        };
    }


    private static String getValue(String value) {
        return CHAR + value + CHAR;
    }

}
