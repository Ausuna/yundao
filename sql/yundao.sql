CREATE DATABASE yundao

USE yundao

/*创建用户信息表*/
CREATE TABLE user_info
(
   id                    int NOT NULL auto_increment,
   user_id               VARCHAR(64) NOT NULL UNIQUE,
   user_name             nvarchar(64),
	 role_id               varchar(64),
   tel                   varchar(64),
   email                 varchar(64),
   create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id),
	 FOREIGN KEY (role_id) REFERENCES role_info(role_id)
);

insert into user_info values (null,'00001','admin','40001','15900000001','15900000001','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00002','teacher','40003','15900000002','15900000002','2019.4.26','admin','2019.4.26','root');
insert into user_info values (null,'00003','student','40002','15900000003','15900000003','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00004','杨宁','40002','100000000','100000000','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00005','姚志良','40002','200000000','200000000','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00006','游纬豪','40002','300000000','300000000','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00007','李文锦','40002','400000000','400000000','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00008','林若圆','40002','500000000','500000000','2019.4.26','admin','2019.4.26','admin');
insert into user_info values (null,'00009','池老板','40003','600000000','600000000','2019.4.26','admin','2019.4.26','admin');


/*创建用户登录表*/
create table user_login
(
   id                    int not null auto_increment,
   user_id               VARCHAR(64) not null,
   account               varchar(64),
   password              varchar(64),
   login_type            varchar(64),
   create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id),
	 FOREIGN KEY (user_id) REFERENCES user_info(user_id)
);

insert into user_login values(null,'00001','admin','123456','username','2019.4.26','admin','2019.4.26','admin');
insert into user_login values(null,'00001','15900000001','123456','tel','2019.4.26','admin','2019.4.26','admin');
insert into user_login values(null,'00002','teacher','123456','username','2019.4.26','admin','2019.4.26','admin');
insert into user_login values(null,'00002','15900000002','123456','tel','2019.4.26','admin','2019.4.26','admin');
insert into user_login values(null,'00003','student','123456','username','2019.4.26','admin','2019.4.26','admin');
insert into user_login values(null,'00003','15900000003','123456','tel','2019.4.26','admin','2019.4.26','admin');

/*创建角色表*/
create table role_info
(
   id                    int not null auto_increment,
   role_id               varchar(64) UNIQUE,
   role_name             varchar(64),
   create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id)
);

insert into role_info values(null,'40001','管理员','2019.4.26','root','2019.4.26','root');
insert into role_info values(null,'40002','学生','2019.4.26','root','2019.4.26','root');
insert into role_info values(null,'40003','老师','2019.4.26','root','2019.4.26','root');

/*创建数据字典表*/
create table dict_info
(
   id                    int not null auto_increment,
   dict_id               varchar(64) not null UNIQUE,
   dict_desc             nvarchar(256),
   create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id)
);

insert into dict_info values(null,'10001','学院','2019.4.26','admin','2019.4.26','admin');

/*创建数据字典详细信息表*/
create table dict_detail
(
   id                    int not null auto_increment,
   dict_id               varchar(64) not null,
   item_id               varchar(64) not null,
   item_name             nvarchar(64),
   is_default            bit,
   dict_order            int,
   create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id),
	 FOREIGN KEY (dict_id) REFERENCES dict_info (dict_id)
);

insert into dict_detail values(null,'10001',1,'数计学院',1,1,'2019.4.26','admin','2019.4.26','admin');
insert into dict_detail values(null,'10001',2,'经管学院',0,2,'2019.4.26','admin','2019.4.26','admin');
insert into dict_detail values(null,'10001',3,'土木学院',0,3,'2019.4.26','admin','2019.4.26','admin');

/*创建课时表*/
create table time_info
(
	id                    int not null auto_increment,
	time_id               VARCHAR(64) not null UNIQUE,
	time_type             varchar(64),
	time_start            time,
	time_end              time,
	create_date           datetime,
  create_by             varchar(64),
  modify_date           datetime,
  modify_by             varchar(64),
  primary key (id)
);

insert into time_info values(null,'30001','夏','8:30:0','10:0:0','2019.4.26','admin','2019.4.26','admin');
insert into time_info values(null,'30002','夏','10:20:0','12:0:0','2019.4.26','admin','2019.4.26','admin');
insert into time_info values(null,'30003','夏','14:0:0','15:40:0','2019.4.26','admin','2019.4.26','admin');
insert into time_info values(null,'30004','夏','15:50:0','17:30:0','2019.4.26','admin','2019.4.26','admin');

/*创建班课信息表*/
create table class_info
(
	id                    int not null auto_increment,
	class_id              VARCHAR(64) not null UNIQUE,
	class_name            nvarchar(64),
	class_course          nvarchar(64),
	class_teacher         VARCHAR(64) not null,
	class_time            VARCHAR(64),
	class_status          bit,
	class_student         int,
	class_college         nvarchar(64),
	class_term            nvarchar(64),
	class_detail          nvarchar(512),
	create_date           datetime,
	create_by             varchar(64),
	modify_date           datetime,
	modify_by             varchar(64),
	primary key (id),
	FOREIGN KEY (class_teacher) REFERENCES user_info (user_id),
	FOREIGN KEY (class_time) REFERENCES time_info (time_id)
);

insert into class_info values(null,'20001','计算机01班','工程训练','00009','30001',1,10,'数计学院','2018-2019下学期','无','2019.4.26','admin','2019.4.26','admin');
insert into class_info values(null,'20002','计算机02班','专业英语','00002','30002',1,10,'数计学院','2018-2019下学期','无','2019.4.26','admin','2019.4.26','admin');
insert into class_info values(null,'20003','计算机03班','智能技术','00002','30003',1,10,'数计学院','2018-2019下学期','无','2019.4.26','admin','2019.4.26','admin');

/*创建班课——用户对应表*/
create table user_class
(
   id                     int not null auto_increment,
   user_id                VARCHAR(64) not null,
   class_id               VARCHAR(64) not null,
	 grade                  int,
	 create_date            datetime,
   create_by              varchar(64),
   modify_date            datetime,
   modify_by              varchar(64),
   primary key (id),
	 FOREIGN KEY (user_id)  REFERENCES user_info (user_id),
	 FOREIGN KEY (class_id) REFERENCES class_info (class_id)
);

insert into user_class values(null,'00003','20001','40002',10,'2019.4.26','admin','2019.4.26','admin');

/*创建菜单信息表*/
create table menu_info
(
   id                    int not null auto_increment,
   menu_id               VARCHAR(64) not null UNIQUE,
   menu_name             nvarchar(64),
   menu_path             varchar(128),
   menu_parent           varchar(128),
   menu_order            int,
   menu_icon             varchar(128),
   create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id)
);

/*创建角色-菜单表*/
create table role_menu
(
   id                    int not null auto_increment,
   role_id               VARCHAR(64) not null,
   menu_id               VARCHAR(64) not null,
	 create_date           datetime,
   create_by             varchar(64),
   modify_date           datetime,
   modify_by             varchar(64),
   primary key (id),
	 FOREIGN KEY (role_id) REFERENCES role_info (role_id),
	 FOREIGN KEY (menu_id) REFERENCES menu_info (menu_id)
);

/*创建点名表*/
create table call_info
(
	id                    int not null auto_increment,
	call_id               varchar(64) not null UNIQUE,
	class_id              varchar(64) not null,
	create_date           datetime,
  create_by             varchar(64),
  modify_date           datetime,
  modify_by             varchar(64),
  primary key (id),
	FOREIGN KEY (class_id) REFERENCES class_info (class_id)
);

/*创建点名详细表*/
create table call_detail
(
	id                    int not null auto_increment,
	call_id               varchar(64) not null,
	user_id               varchar(64),
	create_date           datetime,
  create_by             varchar(64),
  modify_date           datetime,
  modify_by             varchar(64),
  primary key (id),
	FOREIGN KEY (call_id) REFERENCES call_info (call_id),
	FOREIGN KEY (user_id) REFERENCES user_info (user_id)
);