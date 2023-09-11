package com.service.other.criteria;

import com.service.entity.AbstractEntity;
import com.service.entity.ClientEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class ClientUtils {

    private String CHAR = "%";

    public static Specification<ClientEntity> findByValue(String value) {
        return (repair, cq, cb) -> {
            Predicate id = null;

            if (StringUtils.isNumeric(value)) {
                id = cb.equal(repair.get(AbstractEntity.Fields.id), value);
            }

            Predicate shortDescription = cb.like(repair.get(ClientEntity.Fields.name), getValue(value));
            return Objects.nonNull(id) ? cb.or(id, shortDescription) : cb.or(shortDescription);
        };
    }
    private static String getValue(String value) {
        return CHAR + value + CHAR;
    }

}
