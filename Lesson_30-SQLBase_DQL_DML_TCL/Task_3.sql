begin;

insert into "library".books values (5, 'The Martian' , 'Weir Andy', 2011, 'Fantasy');
insert into "library".borrowed_books values (5, 5, 2, '29-01-2026', null, 'borrowed');

rollback;

SELECT * FROM "library".books;
SELECT * FROM "library".borrowed_books;



begin;

insert into "library".books values (5, 'The Martian' , 'Weir Andy', 2011, 'Fantasy');
insert into "library".borrowed_books values (5, 5, 2, '29-01-2026', null, 'borrowed');

commit;

SELECT * FROM "library".books;
SELECT * FROM "library".borrowed_books;
