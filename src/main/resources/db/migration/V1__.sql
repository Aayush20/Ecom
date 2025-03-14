CREATE TABLE category
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1)       NOT NULL,
    category_name   VARCHAR(255) NOT NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_featured_products
(
    category_id          BIGINT NOT NULL,
    featured_products_id BIGINT NOT NULL
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    `description`   VARCHAR(255) NULL,
    price DOUBLE NULL,
    quantity        INT    NOT NULL,
    category_id     BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE category_featured_products
    ADD CONSTRAINT uc_category_featured_products_featuredproducts UNIQUE (featured_products_id);

ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (category_name);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_featured_products
    ADD CONSTRAINT fk_catfeapro_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_featured_products
    ADD CONSTRAINT fk_catfeapro_on_product FOREIGN KEY (featured_products_id) REFERENCES product (id);