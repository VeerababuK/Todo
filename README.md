# Todo
Todo app for internal training.

CREATE TABLE login (
oid int(9) NOT NULL AUTO_INCREMENT,
user_name varchar(60),
password varchar(20),
PRIMARY KEY(oid)
);

CREATE TABLE todo(
oid int(9) NOT NULL AUTO_INCREMENT,
todo_desc varchar(500),
days int(3),
active Boolean,
completed Boolean,
login_oid int(9),
PRIMARY KEY(oid),
FOREIGN KEY(login_oid) REFERENCES login(oid)
);


insert into login(user_name, password) values('veera','veera');
insert into login(user_name, password) values('amar','amar');
insert into login(user_name, password) values('pardhu','pardhu');
insert into login(user_name, password) values('sai','sai');
insert into login(user_name, password) values('durga','durga');
insert into login(user_name, password) values('tejaswini','tejaswini');
