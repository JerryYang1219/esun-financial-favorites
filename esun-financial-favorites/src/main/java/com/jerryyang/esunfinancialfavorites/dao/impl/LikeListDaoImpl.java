package com.jerryyang.esunfinancialfavorites.dao.impl;

import com.jerryyang.esunfinancialfavorites.dao.LikeListDao;
import com.jerryyang.esunfinancialfavorites.dto.LikeListRequest;
import com.jerryyang.esunfinancialfavorites.model.LikeList;
import com.jerryyang.esunfinancialfavorites.model.Product;
import com.jerryyang.esunfinancialfavorites.rowmapper.LikeListRowMapper;
import com.jerryyang.esunfinancialfavorites.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LikeListDaoImpl implements LikeListDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Override
    public Product getProductByNo(Integer no) {
        // 使用具名參數查詢產品，防止 SQL Injection
        String sql = "SELECT no, product_name, price, fee_rate FROM product WHERE no = :no";

        // 建立參數 Map，將 no 映射至 SQL 具名參數
        Map<String, Object> map = new HashMap<>();
        map.put("no", no);

        // 執行查詢，透過 ProductRowMapper 將結果轉換成 Product 物件
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        // 若查詢到資料則回傳第一筆，否則回傳 null
        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createLikeList(String userId, LikeListRequest likeListRequest,
                                  BigDecimal totalFee, BigDecimal totalAmount) {
        // 使用 Stored Procedure 新增喜好清單
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_create_like_list");

        // 傳入 Stored Procedure 的參數
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_user_id", userId)
                .addValue("p_no", likeListRequest.getNo())
                .addValue("p_purchase_quantity", likeListRequest.getPurchaseQuantity())
                .addValue("p_account", likeListRequest.getAccount())
                .addValue("p_total_fee", totalFee)
                .addValue("p_total_amount", totalAmount);

        // 執行 Stored Procedure 並取得回傳的 sn
        Map<String, Object> result = jdbcCall.execute(params);

        return ((Number) result.get("p_sn")).intValue();
    }

    @Override
    public LikeList getLikeListBySn(Integer sn) {
        // 使用 LEFT JOIN 串聯 like_list、product、user 三張資料表
        // 一次查詢取得完整資料（產品名稱、使用者電子郵件）
        String sql = "SELECT ll.sn, ll.user_id, ll.no, ll.purchase_quantity, " +
                "ll.account, ll.total_fee, ll.total_amount, p.product_name, u.email " +
                "FROM like_list ll " +
                "LEFT JOIN product p ON ll.no = p.no " +
                "LEFT JOIN user u ON ll.user_id = u.user_id " +
                "WHERE ll.sn = :sn";

        // 建立參數 Map，將 sn 映射至 SQL 具名參數
        Map<String, Object> map = new HashMap<>();
        map.put("sn", sn);

        // 執行查詢，透過 LikeListRowMapper 將結果轉換成 LikeList 物件
        List<LikeList> likeListResult = namedParameterJdbcTemplate.query(sql, map, new LikeListRowMapper());

        // 若查詢到資料則回傳第一筆，否則回傳 null
        if (likeListResult.size() > 0) {
            return likeListResult.get(0);
        } else {
            return null;
        }
    }
}