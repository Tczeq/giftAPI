USE gift;
CREATE TABLE `kid` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `birthday` DATETIME NOT NULL,
    `deleted` BOOLEAN NOT NULL DEFAULT false,
    `version` INT
);

