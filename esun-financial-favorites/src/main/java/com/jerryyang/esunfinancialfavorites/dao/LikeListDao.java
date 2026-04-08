package com.jerryyang.esunfinancialfavorites.dao;

import com.jerryyang.esunfinancialfavorites.dto.LikeListRequest;
import com.jerryyang.esunfinancialfavorites.model.LikeList;
import com.jerryyang.esunfinancialfavorites.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface LikeListDao {

    // 根據產品流水號查詢產品資料
    Product getProductByNo(Integer no);

    // 新增喜好金融商品
    Integer createLikeList(String userId, LikeListRequest likeListRequest,
                           BigDecimal totalFee, BigDecimal totalAmount);

    // 根據 sn 查詢單筆喜好清單
    LikeList getLikeListBySn(Integer sn);

    //查詢使用者的所有喜好清單
    List<LikeList> getLikeListByUserId(String userId);

    //刪除喜好清單
    void deleteLikeListBySn(Integer sn);
}
