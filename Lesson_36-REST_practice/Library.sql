CREATE SCHEMA Library;

CREATE TABLE IF NOT EXISTS Library.books
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_year INT CHECK (published_year > 0),
    genre VARCHAR(100)
)

CREATE TABLE IF NOT EXISTS Library.readers
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(15) UNIQUE
)

CREATE TABLE IF NOT EXISTS Library.borrowed_books
(
    id SERIAL PRIMARY key,
    book_id INT NOT null,
    reader_id INT NOT null,
    borrow_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) CHECK (status IN ('borrowed', 'returned')),
    FOREIGN KEY (book_id)  REFERENCES Library.books (id) ON DELETE CASCADE,
    FOREIGN KEY (reader_id)  REFERENCES Library.readers (id) ON DELETE CASCADE
)

CREATE INDEX title_hash_idx ON Library.books using hash (title);

CREATE INDEX reader_id_idx ON Library.borrowed_books (reader_id) WHERE (status = 'borrowed');