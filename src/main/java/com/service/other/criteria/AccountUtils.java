package com.service.other.criteria;

import com.service.entity.AbstractEntity;
import com.service.entity.AccountEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class AccountUtils {

    private String CHAR = "%";

    public static Specification<AccountEntity> findByValue(String value) {
        return (repair, cq, cb) -> {
            Predicate id = null;

            if (StringUtils.isNumeric(value)) {
                id = cb.equal(repair.get(AbstractEntity.Fields.id), value);
            }

            Predicate name = cb.like(repair.get(AccountEntity.Fields.name), getValue(value));
            return Objects.nonNull(id) ? cb.or(id, name) : cb.or(name);
        };
    }
    private static String getValue(String value) {
        return CHAR + value + CHAR;
    }

}
