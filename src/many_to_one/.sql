USE mybatis;

create table product_(
id int NOT NULL AUTO_INCREMENT,
name varchar(30)  DEFAULT NULL,
price float  DEFAULT 0,
cid int ,
PRIMARY KEY (id)
)AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

delete from category_;
INSERT INTO category_ VALUES (1,'category1');
INSERT INTO category_ VALUES (2,'category2');
delete from product_;
INSERT INTO product_ VALUES (1,'product a', 88.88, 1);
INSERT INTO product_ VALUES (2,'product b', 88.88, 1);
INSERT INTO product_ VALUES (3,'product c', 88.88, 1);
INSERT INTO product_ VALUES (4,'product x', 88.88, 2);
INSERT INTO product_ VALUES (5,'product y', 88.88, 2);
INSERT INTO product_ VALUES (6,'product z', 88.88, 2);