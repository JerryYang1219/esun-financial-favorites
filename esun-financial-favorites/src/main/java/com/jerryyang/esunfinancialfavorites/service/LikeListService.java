package com.jerryyang.esunfinancialfavorites.service;

import com.jerryyang.esunfinancialfavorites.dto.LikeListRequest;
import com.jerryyang.esunfinancialfavorites.dto.LikeListResponse;
import com.jerryyang.esunfinancialfavorites.model.LikeList;
import com.jerryyang.esunfinancialfavorites.model.Product;

import java.util.List;

public interface LikeListService {

    // 新增喜好金融商品
    Integer createLikeList(String userId, LikeListRequest likeListRequest);

    // 查詢剛新增好的喜好金融商品清單
    LikeList getLikeListBySn(Integer userId);

    // 查詢
    List<LikeListResponse> getLikeListByUserId(String userId);

    // 刪除喜好金融商品
    void deleteLikeListBySn(Integer sn);

    // 更改喜好金融商品
    void updateLikeList(Integer sn, LikeListRequest likeListRequest);

    // 顯示所有商品
    List<Product> getProductList();
}
