# 金融商品喜好紀錄系統

金融商品喜好紀錄系統

## 系統架構

- **前端**：Vue.js (CDN)
- **後端**：Spring Boot 3.5.13 / Java 17
- **資料庫**：MySQL
- **建構工具**：Maven

## 功能說明

1. **新增喜好金融商品** - 使用者可新增喜好的金融商品資訊、扣款帳號、購買數量
2. **查詢喜好金融商品清單** - 查詢喜好清單，包含產品名稱、扣款帳號、總金額、手續費、電子信箱
3. **刪除喜好金融商品** - 刪除指定的喜好金融商品
4. **更改喜好金融商品** - 更改喜好金融商品資訊

## 技術說明

- 使用 **RESTful API** 風格建立後端服務
- 透過 **Stored Procedure** 存取資料庫
- 使用 **Transaction** 確保資料一致性
- 防止 **SQL Injection** 及 **XSS** 攻擊

## 專案結構
```text
src/main/java/com/jerryyang/esunfinancialfavorites/
├── controller/        # 展示層 
├── service/           # 業務層 
│   └── impl/
├── dao/               # 資料層 
│   └── impl/
├── model/             # 資料物件 
├── dto/               # 資料傳輸物件 
├── rowmapper/         # 資料映射 
└── XssFilter.java     # XSS 安全防護
```

## 資料庫

資料庫相關檔案放在 `/DB` 資料夾：
- `schema.sql` - 資料表建立語法
- `data.sql` - 測試資料
- `stored_procedures.sql` - 預存程序

## 如何啟動
### 1. 資料庫準備
- 建立資料庫：`CREATE DATABASE esun_financial CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`
- 依序執行 `/DB` 資料夾下的腳本：
  1. `schema.sql` (建立資料表)
  2. `data.sql` (匯入測試資料)
  3. `stored_procedures.sql` (建立預存程序)

### 2. 後端設定
- 修改 `src/main/resources/application.properties` 中的資料庫帳密。
- 使用 Maven 編譯並執行：`mvn spring-boot:run`

### 3. 存取系統
- 前端入口：[http://localhost:8080/index.html](http://localhost:8080/index.html)
- 測試帳號可用：`A1236456789` (王小明)
