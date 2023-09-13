package com.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/page/repair/{repairId}")
    public String repair(){
        return "repair";
    }

    @GetMapping("/repair/create")
    public String createRepair(){
        return "new-repair";
    }

    @GetMapping("/clients")
    public String clients(){
        return "clients";
    }

    @GetMapping("/client/create")
    public String createClient(){
        return "new-client";
    }

    @GetMapping("/page/client/{clientId}")
    public String clientInfo(){
        return "client-info";
    }

    @GetMapping("/accounts")
    public String accounts(){
        return "accounts";
    }

    @GetMapping("/account/create")
    public String createAccount(){
        return "new-account";
    }

}
