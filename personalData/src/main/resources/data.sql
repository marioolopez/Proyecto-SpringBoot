DROP TABLE IF EXISTS personal_data;

CREATE TABLE `personal_data` (
  `person_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL
);

INSERT INTO `personal_data` (`name`,`email`,`mobile_number`,`address`)
 VALUES ('Alfredo Leal','aleal@gmail.com','44543234', 'Santiago 123');
 
 INSERT INTO `personal_data` (`name`,`email`,`mobile_number`,`address`)
 VALUES ('Joaquín Leal','joaleal@gmail.com','4433333', 'Concepción 443');
 
