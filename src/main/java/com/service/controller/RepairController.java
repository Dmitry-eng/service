package com.service.controller;

import com.service.dto.account.RepairCreate;
import com.service.dto.account.ShortInfoRepair;
import com.service.service.RepairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public void createRepair(@RequestBody @Valid RepairCreate repairCreate) {
        repairService.createRepair(repairCreate);
    }

    @GetMapping("/all/{value}")
    public List<ShortInfoRepair> findAllRepair(@PathVariable(required = false) String value) {
        return repairService.findAllRepair(value);
    }
}
