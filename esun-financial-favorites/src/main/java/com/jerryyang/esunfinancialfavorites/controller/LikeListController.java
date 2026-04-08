package com.jerryyang.esunfinancialfavorites.controller;

import com.jerryyang.esunfinancialfavorites.dto.LikeListRequest;
import com.jerryyang.esunfinancialfavorites.dto.LikeListResponse;
import com.jerryyang.esunfinancialfavorites.model.LikeList;
import com.jerryyang.esunfinancialfavorites.service.LikeListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeListController {

    @Autowired
    private LikeListService likeListService;

    // 新增喜好金融商品
    @PostMapping("/users/{userId}/likelist")
    public ResponseEntity<LikeList> createLikeList(
            @PathVariable String userId, // 從 URL 路徑取得使用者 ID
            @RequestBody @Valid LikeListRequest likeListRequest) {  // 接收並驗證前端傳來的請求參數

        // 呼叫 Service 層建立喜好清單，並取得資料庫自動生成的流水序號 sn
        Integer sn = likeListService.createLikeList(userId, likeListRequest);

        // 根據 sn 查詢剛建立的喜好清單資料
        LikeList likeList = likeListService.getLikeListBySn(sn);

        // 回傳 HTTP 201 Created，並將喜好清單資料放在 Response Body 中回傳給前端
        return ResponseEntity.status(HttpStatus.CREATED).body(likeList);
    }

    //查詢喜好金融商品清單
    @GetMapping("/users/{userId}/likelist")
    public ResponseEntity<List<LikeListResponse>> getLikeList(@PathVariable String userId) {

        List<LikeListResponse> likeListResponseList = likeListService.getLikeListByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(likeListResponseList);
    }

}
