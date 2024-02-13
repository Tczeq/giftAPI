USE gift;
CREATE TABLE gift (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    price DOUBLE,
    kid_id INT,
    deleted BOOLEAN DEFAULT FALSE,
    version INT,
    FOREIGN KEY (kid_id) REFERENCES kid(id)
);
