create schema if not exists addressbook

create table if not exists addressbook.users(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE NOT NULL
);

create table if not exists addressbook.addresses(
	id SERIAL PRIMARY key,
    	user_id INT REFERENCES addressbook.users(id) ON DELETE CASCADE,
    	street VARCHAR(255) NOT NULL,
    	city VARCHAR(100) NOT NULL,
    	postal_code VARCHAR(20)
);

create table if not exists addressbook.authorities(
    	id SERIAL PRIMARY key,
    	user_id INT REFERENCES addressbook.users(id) ON DELETE CASCADE,
    	authority VARCHAR(100)
);