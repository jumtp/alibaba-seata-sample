package com.example.account.service.impl;
import com.example.account.BalanceInfo;
import com.example.account.entity.Balance;
import com.example.account.repository.BalanceRepository;
import com.example.account.service.IBalanceService;
import com.example.filter.AccessContext;
import com.example.vo.LoginUserInfo;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class BalanceServiceImpl implements IBalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public BalanceInfo getCurrentUserBalanceInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        BalanceInfo balanceInfo = new BalanceInfo(loginUserInfo.id(), 0L);

        Optional<Balance> balanceOptional = balanceRepository.findByUserId(loginUserInfo.id());
        Balance balance;
        if (balanceOptional.isEmpty()) {
            balance = new Balance()
                    .setUserId(loginUserInfo.id())
                    .setBalance(0L);
            balanceRepository.insert(balance);
            balanceInfo.setBalance(balance.getBalance());
        } else {
            balanceInfo.setBalance(balanceOptional.get().getBalance());
        }

        return balanceInfo;
    }

    @Override
    @Transactional
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {
        log.info("BalanceServiceImpl XID : [{}]", RootContext.getXID());

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        Optional<Balance> balanceOptional = balanceRepository.findByUserId(loginUserInfo.id());
        if (balanceOptional.isEmpty()
                || balanceOptional.get().getBalance() - balanceInfo.getBalance() < 0
        ) {
            throw new RuntimeException("用户余额不足");
        }

        Balance balance = balanceOptional.get();

        Long sourceBalance = balance.getBalance();
        balance.setBalance(sourceBalance - balanceInfo.getBalance());
        balanceRepository.updateById(balance);

        return new BalanceInfo(
                balance.getId(),
                balance.getBalance()
        );
    }
}
