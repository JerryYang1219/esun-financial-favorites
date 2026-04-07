USE esun_financial;

-- 新增測試使用者
INSERT INTO user (user_id, user_name, email, account) VALUES
 ('A1236456789', '王小明', 'test@email.com', '1111999666'),
 ('B9876543210', '李明智', 'test2@email.com', '2222888777');

-- 新增測試產品
INSERT INTO product (product_name, price, fee_rate) VALUES
 ('產品A', 1000.00, 0.01),
 ('產品B', 500.00, 0.005),
 ('產品C', 3000.00, 0.02);