package com.service.dto.account;

import lombok.Data;

@Data
public class Client {

    private Long id;

    private String phoneNumber;

    private String email;

    private String name;
}
