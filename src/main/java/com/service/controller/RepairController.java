package com.service.controller;

import com.service.dto.account.CommentCreate;
import com.service.dto.account.FullInfoRepair;
import com.service.dto.account.RepairCreate;
import com.service.dto.account.RepairUpdate;
import com.service.dto.account.ShortInfoRepair;
import com.service.other.marker.RepairUpdatePrice;
import com.service.other.marker.RepairUpdateStatus;
import com.service.service.RepairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("{id}")
    public FullInfoRepair findById(@PathVariable Long id) {
        return repairService.findById(id);
    }

    @GetMapping({"/all/{value}", "/all"})
    public List<ShortInfoRepair> findAllRepair(@PathVariable(required = false) String value) {
        return repairService.findAllRepair(value);
    }

    @GetMapping("/all/client/{id}")
    public List<ShortInfoRepair> findAllByClient(@PathVariable(required = false) Long id) {
        return repairService.findAllByClient(id);
    }

    @PutMapping("/updateDescription")
    public void updateDescription(@RequestBody @Valid
                                  RepairUpdate repairUpdate) {
        repairService.updateDescription(repairUpdate);
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestBody @Validated(RepairUpdateStatus.class)
                             RepairUpdate repairUpdate) {
        repairService.updateStatus(repairUpdate);
    }

    @PutMapping("/updateExecutor")
    public void updateExecutor(@RequestBody @Valid
                               RepairUpdate repairUpdate) {
        repairService.updateExecutor(repairUpdate);
    }

    @PutMapping("/updatePrice")
    public void updatePrice(@RequestBody @Validated(RepairUpdatePrice.class)
                            RepairUpdate repairUpdate) {
        repairService.updatePrice(repairUpdate);
    }

    @PostMapping("/comment")
    public void addComment(@RequestBody @Valid
                           CommentCreate commentCreate) {
        repairService.createComment(commentCreate);
    }

}
