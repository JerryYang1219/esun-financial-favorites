package com.jerryyang.esunfinancialfavorites.service;

import com.jerryyang.esunfinancialfavorites.dto.LikeListRequest;
import com.jerryyang.esunfinancialfavorites.model.LikeList;

import java.util.List;

public interface LikeListService {

    // 新增喜好金融商品
    Integer createLikeList(String userId, LikeListRequest likeListRequest);

    // 查詢喜好金融商品清單
    LikeList getLikeListBySn(Integer userId);
}
