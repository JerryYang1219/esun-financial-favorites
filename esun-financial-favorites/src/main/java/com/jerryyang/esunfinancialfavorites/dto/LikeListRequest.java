package com.jerryyang.esunfinancialfavorites.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LikeListRequest {

    @NotNull
    private Integer no;               // 選擇的產品流水號

    @NotNull
    private Integer purchaseQuantity; // 購買數量

    @NotBlank
    private String account;           // 扣款帳號

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
