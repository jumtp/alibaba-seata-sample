package com.example.account.controller;

import com.example.account.BalanceInfo;
import com.example.account.service.IBalanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final IBalanceService iBalanceService;

    public BalanceController(IBalanceService balanceService) {
        this.iBalanceService = balanceService;
    }

    @GetMapping("/current-balance")
    public BalanceInfo getCurrentUserBalanceInfo(){
        return iBalanceService.getCurrentUserBalanceInfo();
    }

    @PostMapping("/deduct-balance")
    public BalanceInfo deductBalance(@RequestBody BalanceInfo balanceInfo){
        return iBalanceService.deductBalance(balanceInfo);
    }

}
