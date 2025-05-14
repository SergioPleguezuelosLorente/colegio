CREATE TABLE IF NOT EXISTS `class` (
    `class_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`class_id`)
);

ALTER TABLE `person`
ADD COLUMN `class_id` int NULL AFTER `address_id`,
ADD CONSTRAINT `FK_CLASS_CLASS_ID` FOREIGN KEY (`class_id`)
REFERENCES `class` (`class_id`);