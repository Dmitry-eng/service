package com.service.dto.account;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RepairStatus {
    NEW("Новый"),
    IN_PROGRESS("В процессе ремонта"),
    PAUSE("Пауза"),
    DONE("Завершен"),
    REJECT("Отклонен");

    @JsonValue
    private final String value;
}
