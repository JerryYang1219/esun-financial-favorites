package com.jerryyang.esunfinancialfavorites.model;

import java.math.BigDecimal;

public class LikeList {
    private Integer sn;               // 流水序號 (PK)
    private String userId;            // 使用者 ID (FK)
    private Integer no;               // 產品流水號 (FK)
    private Integer purchaseQuantity; // 購買數量
    private String account;           // 扣款帳號
    private BigDecimal totalFee;      // 總手續費用
    private BigDecimal totalAmount;   // 預計扣款總金額

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
