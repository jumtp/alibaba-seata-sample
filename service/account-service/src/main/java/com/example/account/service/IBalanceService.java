package com.example.account.service;


import com.example.account.BalanceInfo;

public interface IBalanceService {
    BalanceInfo getCurrentUserBalanceInfo();

    BalanceInfo deductBalance(BalanceInfo balanceInfo);
}
