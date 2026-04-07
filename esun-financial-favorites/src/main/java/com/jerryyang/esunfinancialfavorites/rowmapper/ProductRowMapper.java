package com.jerryyang.esunfinancialfavorites.rowmapper;

import com.jerryyang.esunfinancialfavorites.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setNo(rs.getInt("no"));
        product.setProductName(rs.getString("product_name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setFeeRate(rs.getBigDecimal("fee_rate"));
        return product;
    }
}
