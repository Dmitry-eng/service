package com.service.dto.repair;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;


@RequiredArgsConstructor
public enum RepairStatus {
    NEW("Новый", false),
    DIAGNOSTICS("Диагностика", false),
    ESTIMATION("Оценка стоимости ремонта", false),
    APPROVAL("Согласование стоимости ремонта", false),
    IN_PROGRESS("В процессе ремонта", false),
    PAUSE("Пауза", false),
    DONE("Завершен", true),
    REJECT("Отклонен", true);

    @JsonValue
    private final String value;

    @Getter
    private final boolean isFinal;

    private static final RepairStatus[] values = RepairStatus.values();

    @JsonCreator
    public static RepairStatus of(String value) {
        return Stream.of(values)
                .filter(repairStatus -> repairStatus.value.equals(value))
                .findFirst()
                .orElse(null);
    }
}
