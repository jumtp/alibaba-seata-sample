package com.example.order.feign;

import com.example.account.BalanceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(contextId = "BalanceClient", value = "account-service")
public interface BalanceClient {

    @RequestMapping(value = "/balance/deduct-balance", method = RequestMethod.POST)
    BalanceInfo deductBalance(BalanceInfo balanceInfo);
}
