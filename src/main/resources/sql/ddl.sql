create table authority
(
	authority_id bigint auto_increment
		primary key,
	member_id bigint not null,
	role varchar(30) not null,
	created_at datetime null,
	updated_at datetime null,
	constraint authority_uk
		unique (member_id, role)
);

create table category
(
	category_id bigint auto_increment
		primary key,
	name varchar(30) not null,
	created_at datetime null,
	updated_at datetime null,
	constraint category_name_uindex
		unique (name)
);

create table category_item
(
	category_item_id bigint auto_increment
		primary key,
	category_id bigint null,
	item_id bigint null,
	created_at datetime null,
	updated_at datetime null
);

create table delivery
(
	delivery_id bigint auto_increment
		primary key,
	order_id bigint not null,
	delivery_status varchar(30) not null,
	city varchar(50) not null,
	zipcode varchar(20) not null,
	street varchar(100) not null,
	created_at datetime null,
	updated_at datetime null
);

create table item
(
	item_id bigint auto_increment
		primary key,
	name varchar(100) not null,
	price int not null,
	stock_quantity int not null,
	dtype varchar(20) null,
	artist varchar(30) null,
	etc varchar(30) null,
	author varchar(30) null,
	isbn varchar(30) null,
	director varchar(30) null,
	actor varchar(30) null,
	created_at datetime null,
	updated_at datetime null,
	constraint item_name_uindex
		unique (name)
);

create table member
(
	member_id bigint auto_increment
		primary key,
	username varchar(50) not null,
	password varchar(100) not null,
	tel varchar(20) not null,
	city varchar(50) not null,
	zipcode varchar(20) null,
	street varchar(100) not null,
	created_at datetime null,
	updated_at datetime null,
	constraint member_username_uindex
		unique (username)
);

create table order_item
(
	order_item_id bigint auto_increment
		primary key,
	order_id bigint not null,
	item_id bigint not null,
	order_price int not null,
	count int not null,
	created_at datetime null,
	updated_at datetime null
);

create table orders
(
	order_id bigint auto_increment
		primary key,
	member_id bigint not null,
	order_status varchar(30) not null,
	created_at datetime null,
	updated_at datetime null
);

