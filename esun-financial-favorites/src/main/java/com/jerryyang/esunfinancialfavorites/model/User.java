package com.jerryyang.esunfinancialfavorites.model;

// 使用者資料模型，對應資料庫 user 資料表
public class User {
    private String userId;   // 使用者 ID (PK)
    private String userName; // 使用者名稱
    private String email;    // 使用者電子郵件
    private String account;  // 扣款帳號

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
