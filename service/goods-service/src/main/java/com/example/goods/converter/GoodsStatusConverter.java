package com.example.goods.converter;

import com.example.goods.constant.GoodsStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedTypes({GoodsStatus.class})
@MappedJdbcTypes(JdbcType.INTEGER)
public class GoodsStatusConverter extends BaseTypeHandler<GoodsStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GoodsStatus goodsStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,goodsStatus.getStatus());
    }

    @Override
    public GoodsStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return GoodsStatus.of(resultSet.getInt(s));
    }

    @Override
    public GoodsStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return GoodsStatus.of(resultSet.getInt(i));
    }

    @Override
    public GoodsStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return GoodsStatus.of(callableStatement.getInt(i));
    }
}
