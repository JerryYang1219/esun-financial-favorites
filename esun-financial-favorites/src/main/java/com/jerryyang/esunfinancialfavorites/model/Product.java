package com.jerryyang.esunfinancialfavorites.model;
import java.math.BigDecimal;

// 產品資料模型，對應資料庫 product 資料表
public class Product {
    private Integer no;          // 產品流水號 (PK)
    private String productName;  // 產品名稱
    private BigDecimal price;    // 產品價格
    private BigDecimal feeRate;  // 手續費率

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
}
