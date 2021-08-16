
CREATE TABLE `category` (
                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                     `name` VARCHAR(45) NOT NULL,
                                     `seal` INT default 0,
                                     `data_create` timestamp default current_timestamp,
                                     `data_update` timestamp default current_timestamp,
                                     `data_delete` timestamp NULL,
                                     PRIMARY KEY (`id`));

insert into `category` (name,seal) values
('спорт товары',0),
('хозяйственный отдел',0),
('продукты питания',0),
('сезонная скидка',10);

CREATE TABLE `info` (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT,
                                 `url_JPEG` VARCHAR(255) NULL,
                                 `url_info` VARCHAR(255) NULL,
                                 `data_create` timestamp default current_timestamp,
                                 `data_update` timestamp default current_timestamp,
                                 `data_delete` timestamp NULL,
                                 PRIMARY KEY (`id`));

CREATE TABLE `product` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                    `name` VARCHAR(45) NOT NULL,
                                    `price` FLOAT NOT NULL,
                                    `id_info` BIGINT NULL,
                                    `data_create` timestamp default current_timestamp,
                                    `data_update` timestamp default current_timestamp,
                                    `data_delete` timestamp NULL,
                                    PRIMARY KEY (`id`),
                                    FOREIGN KEY (`id_info`)
                                        REFERENCES `info` (`id`)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION);

insert into `product` (name , Price ) values
('хлеб',125.3),
('молоко',12.1),
('box',321.2),
('настольный футбол',123.4);

CREATE TABLE `product_category`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `id_Product` BIGINT NOT NULL,
    `id_Category` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_Product`)
        REFERENCES `product` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`id_Category`)
        REFERENCES `category` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

insert into `product_category` (id_Product,id_Category) values
(1,3),
(1,4),
(2,3),
(3,2),
(4,1);






