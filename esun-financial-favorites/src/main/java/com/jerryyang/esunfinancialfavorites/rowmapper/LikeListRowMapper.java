package com.jerryyang.esunfinancialfavorites.rowmapper;

import com.jerryyang.esunfinancialfavorites.model.LikeList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeListRowMapper implements RowMapper<LikeList> {

    @Override
    public LikeList mapRow(ResultSet rs, int rowNum) throws SQLException {
        LikeList likeList = new LikeList();
        likeList.setSn(rs.getInt("sn"));
        likeList.setUserId(rs.getString("user_id"));
        likeList.setNo(rs.getInt("no"));
        likeList.setPurchaseQuantity(rs.getInt("purchase_quantity"));
        likeList.setAccount(rs.getString("account"));
        likeList.setTotalFee(rs.getBigDecimal("total_fee"));
        likeList.setTotalAmount(rs.getBigDecimal("total_amount"));
        likeList.setProductName(rs.getString("product_name"));

        return likeList;
    }
}
