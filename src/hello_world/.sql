CREATE database mybatis;
USE mybatis;

CREATE TABLE category_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO category_ VALUES (null,'category1');
INSERT INTO category_ VALUES (null,'category2');