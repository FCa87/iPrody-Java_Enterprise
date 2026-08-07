create schema if not exists library

create table if not exists library.authors (
    id SERIAL PRIMARY key,
    name VARCHAR(255) NOT null,
    country VARCHAR(100)
);

create table if not exists library.books (
    id SERIAL primary key,
    title VARCHAR(255) not null,
    published_year INT not null,
    author_id INT not null,
    foreign key (author_id) references library.authors(id) on delete cascade
);