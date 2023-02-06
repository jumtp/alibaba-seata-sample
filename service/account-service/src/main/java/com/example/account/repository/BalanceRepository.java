package com.example.account.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.account.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface BalanceRepository extends BaseMapper<Balance> {

    @Select("select * from `balance` where user_id = #{userId}")
    Optional<Balance> findByUserId(Long userId);
}
