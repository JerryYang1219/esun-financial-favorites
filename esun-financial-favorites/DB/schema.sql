USE esun_financial;

CREATE TABLE user
(
    user_id   VARCHAR(20)  NOT NULL PRIMARY KEY,
    user_name VARCHAR(50)  NOT NULL,
    email     VARCHAR(256) NOT NULL UNIQUE KEY,
    account   VARCHAR(20)  NOT NULL
);

CREATE TABLE product
(
    no           INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(128)  NOT NULL,
    price        DECIMAL(10,2) NOT NULL,
    fee_rate     DECIMAL(5,4)  NOT NULL
);

CREATE TABLE like_list
(
    sn                INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id           VARCHAR(20)   NOT NULL,
    no                INT           NOT NULL,
    purchase_quantity INT           NOT NULL,
    account           VARCHAR(20)   NOT NULL,
    total_fee         DECIMAL(10,2) NOT NULL,
    total_amount      DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (no) REFERENCES product(no)
);