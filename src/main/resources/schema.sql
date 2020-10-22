
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT PRIMARY KEY AUTO_INCREMENT,
  created_at date not null,
  updated_at date not null,
  first_name VARCHAR (255) NOT NULL,
  last_name VARCHAR (255) NOT NULL,
  salary int not null
);

DROP TABLE IF EXISTS address;

CREATE TABLE address (
  id INT PRIMARY KEY AUTO_INCREMENT,
  street VARCHAR (255) NOT NULL,
  city VARCHAR (255) NOT NULL,
  created_at date not null,
  updated_at date not null,
  employee_id INT NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);

