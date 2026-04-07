package com.jerryyang.esunfinancialfavorites.service.impl;

import com.jerryyang.esunfinancialfavorites.dao.LikeListDao;
import com.jerryyang.esunfinancialfavorites.dto.LikeListRequest;
import com.jerryyang.esunfinancialfavorites.model.LikeList;
import com.jerryyang.esunfinancialfavorites.model.Product;
import com.jerryyang.esunfinancialfavorites.service.LikeListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Component
public class LikeListServiceImpl implements LikeListService{

    private final static Logger log = LoggerFactory.getLogger(LikeListServiceImpl.class);

    @Autowired
    private LikeListDao likeListDao;

    @Transactional // 同時異動 product 和 like_list 兩張資料表，需確保一致性
    @Override
    public Integer createLikeList(String userId, LikeListRequest likeListRequest) {
        // 檢查產品是否存在
        Product product = likeListDao.getProductByNo(likeListRequest.getNo());

        if (product == null) {
            log.warn("產品 {} 不存在", likeListRequest.getNo());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 計算總金額：price * purchaseQuantity
        BigDecimal totalAmount = product.getPrice()
                .multiply(new BigDecimal(likeListRequest.getPurchaseQuantity()));

        // 計算總手續費：totalAmount * feeRate
        BigDecimal totalFee = totalAmount.multiply(product.getFeeRate());

        // 呼叫 DAO 建立喜好清單並回傳 sn
        return likeListDao.createLikeList(userId, likeListRequest, totalFee, totalAmount);
    }

    @Override
    public LikeList getLikeListBySn(Integer sn) {
        // 根據 sn 查詢單筆喜好清單
        return likeListDao.getLikeListBySn(sn);
    }

}