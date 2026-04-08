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
        // 透過 SimpleJdbcCall 呼叫 Stored Procedure sp_get_product_by_no
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_get_product_by_no")
                .returningResultSet("product", new ProductRowMapper());

        // 建立參數來源，將 Java 的 no 值對應到 SP 的 IN 參數 p_no
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_no", no); // 傳入產品流水號

        // 執行 Stored Procedure，回傳結果放在 Map 中
        Map<String, Object> result = jdbcCall.execute(params);

        // 從 Map 中用 key "product" 取出查詢結果清單
        List<Product> productList = (List<Product>) result.get("product");

        // 若查詢到資料則回傳第一筆，否則回傳 null
        if (productList != null && productList.size() > 0) {
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
        // 透過 SimpleJdbcCall 呼叫 Stored Procedure sp_get_like_list_by_sn
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_get_like_list_by_sn")
                .returningResultSet("likeList", new LikeListRowMapper());

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_sn", sn); // 傳入流水序號

        Map<String, Object> result = jdbcCall.execute(params);

        List<LikeList> likeListResult = (List<LikeList>) result.get("likeList");

        // 若查詢到資料則回傳第一筆，否則回傳 null
        if (likeListResult != null && likeListResult.size() > 0) {
            return likeListResult.get(0);
        } else {
            return null;
        }
    }

    //查詢使用者喜好清單
    @Override
    public List<LikeList> getLikeListByUserId(String userId) {
        // 透過 SimpleJdbcCall 呼叫 Stored Procedure sp_get_like_list
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("sp_get_like_list")
                .returningResultSet("likeList", new LikeListRowMapper());

        // 建立參數來源，將 userId 傳入 SP 的 IN 參數 p_user_id
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_user_id", userId);

        // 執行 Stored Procedure，取得結果
        Map<String, Object> result = jdbcCall.execute(params);

        // 從 Map 中取出喜好清單
        return (List<LikeList>) result.get("likeList");
    }
}