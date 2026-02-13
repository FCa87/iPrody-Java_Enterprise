insert into "library".books (title, author, published_year, genre) values ('The adventures of Tom Sawyer', 'Twain Mark', 1876, 'Novel');
insert into "library".books (title, author, published_year, genre) values ('Crime and Punishment' , 'Dostoyevsky Fyodor', 1866, 'Novel');
insert into "library".books (title, author, published_year, genre) values ('Hamlet' , 'William Shakespeare', 1601, 'Tragedy');
insert into "library".books (title, author, published_year, genre) values ('Norwegian Wood' , 'Murakami Haruki', 1987, 'Novel');

insert into "library".readers values (1, 'Mike', 'cool@mail.ru', '+79251268754');
insert into "library".readers values (3, 'Robert', 'hot@mail.ru', '+77584632158');
insert into "library".readers values (2, 'Sam', 'ordinary@mail.ru', '+79857775544');
insert into "library".readers values (4, 'Yan', 'notOrdinary@mail.ru', '+71122334455');

insert into "library".borrowed_books values (1, 1, 3, '03-01-2026', '20-01-2026', 'returned');
insert into "library".borrowed_books values (3, 2, 3, '25-01-2026', null, 'borrowed');
insert into "library".borrowed_books values (2, 4, 2, '14-01-2026', null, 'borrowed');
insert into "library".borrowed_books values (4, 3, 1, '25-12-2025', '02-02-2026', 'returned');

UPDATE "library".books SET title = 'The Tragedy of Hamlet', published_year = 1602 WHERE published_year = 1601;
UPDATE "library".borrowed_books SET return_date = '06-02-2026', status = 'returned' WHERE book_id = 2 and reader_id = 3;

DELETE FROM "library".readers WHERE id = 4;
DELETE FROM "library".borrowed_books WHERE book_id = 4;

SELECT * FROM "library".books;
SELECT * FROM "library".readers;
SELECT * FROM "library".borrowed_books;

