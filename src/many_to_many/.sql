USE mybatis;
create table order_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table order_item_(
  id int(11) NOT NULL AUTO_INCREMENT,
  oid int ,
  pid int ,
  number int ,
  PRIMARY KEY(id)
)AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO order_ VALUES (1,'Order001');
INSERT INTO order_ VALUES (2,'Order002');

INSERT INTO order_item_ VALUES (null, 1, 1, 100);
INSERT INTO order_item_ VALUES (null, 1, 2, 100);
INSERT INTO order_item_ VALUES (null, 1, 3, 100);
INSERT INTO order_item_ VALUES (null, 2, 2, 100);
INSERT INTO order_item_ VALUES (null, 2, 3, 100);
INSERT INTO order_item_ VALUES (null, 2, 4, 100);