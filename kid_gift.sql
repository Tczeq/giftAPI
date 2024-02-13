USE gift;
CREATE TABLE `kid_gift` (
    `kid_id` INT,
    `gift_id` INT,
    PRIMARY KEY (`kid_id`, `gift_id`),
    FOREIGN KEY (`kid_id`) REFERENCES `kid`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`gift_id`) REFERENCES `gift`(`id`) ON DELETE CASCADE
);
