CREATE TABLE produtos (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
description VARCHAR(50) NOT NULL,
price DECIMAL(10,2) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into produtos (name, description, price) values ('Notebook', 'Escritório', 100);
insert into produtos (name, description, price) values ('Desktop', 'Escritório', 100);