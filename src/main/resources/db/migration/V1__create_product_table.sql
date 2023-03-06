CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  image VARCHAR(100),
  price INT NOT NULL,
  category VARCHAR(100) NOT NULL,
  quantity INT NOT NULL,
  inventory_status VARCHAR(100),
  rating INT
)