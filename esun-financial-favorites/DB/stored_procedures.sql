USE esun_financial;

DELIMITER //

-- 新增喜好金融商品
CREATE PROCEDURE sp_create_like_list(
    IN p_user_id VARCHAR(20),
    IN p_no INT,
    IN p_purchase_quantity INT,
    IN p_account VARCHAR(20),
    IN p_total_fee DECIMAL(10,2),
    IN p_total_amount DECIMAL(10,2),
    OUT p_sn INT
)
BEGIN
    INSERT INTO like_list (user_id, no, purchase_quantity, account, total_fee, total_amount)
    VALUES (p_user_id, p_no, p_purchase_quantity, p_account, p_total_fee, p_total_amount);
    SET p_sn = LAST_INSERT_ID();
END //

-- 根據產品流水號查詢產品資料
CREATE PROCEDURE sp_get_product_by_no(
    IN p_no INT
)
BEGIN
    SELECT no, product_name, price, fee_rate
    FROM product
    WHERE no = p_no;
END //

-- 根據 sn 查詢單筆喜好清單
CREATE PROCEDURE sp_get_like_list_by_sn(
    IN p_sn INT
)
BEGIN
    SELECT ll.sn, ll.user_id, ll.no, ll.purchase_quantity,
           ll.account, ll.total_fee, ll.total_amount, p.product_name, u.email
    FROM like_list ll
             LEFT JOIN product p ON ll.no = p.no
             LEFT JOIN user u ON ll.user_id = u.user_id
    WHERE ll.sn = p_sn;
END //

-- 查詢該使用者的喜好清單
CREATE PROCEDURE sp_get_like_list(
    IN p_user_id VARCHAR(20)
)
BEGIN
    SELECT ll.sn, ll.user_id, ll.no, ll.purchase_quantity,
           ll.account, ll.total_fee, ll.total_amount, p.product_name, u.email
    FROM like_list ll
             LEFT JOIN product p ON ll.no = p.no
             LEFT JOIN user u ON ll.user_id = u.user_id
    WHERE ll.user_id = p_user_id;
END //

-- 刪除喜好金融商品
CREATE PROCEDURE sp_delete_like_list(
    IN p_sn INT
)
BEGIN
    DELETE FROM like_list WHERE sn = p_sn;
END //

-- 更改喜好金融商品
CREATE PROCEDURE sp_update_like_list(
    IN p_sn INT,
    IN p_no INT,
    IN p_purchase_quantity INT,
    IN p_account VARCHAR(20),
    IN p_total_fee DECIMAL(10,2),
    IN p_total_amount DECIMAL(10,2)
)
BEGIN
    UPDATE like_list
    SET no = p_no,
        purchase_quantity = p_purchase_quantity,
        account = p_account,
        total_fee = p_total_fee,
        total_amount = p_total_amount
    WHERE sn = p_sn;
END //



-- 顯示目前所有商品
CREATE PROCEDURE sp_get_product_list(
)
BEGIN
    SELECT no, product_name, price, fee_rate
    FROM product;
END //

DELIMITER ;
