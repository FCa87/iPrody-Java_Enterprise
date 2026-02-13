select b.title, b.author, bb.reader_id, bb.borrow_date
from "library".books b
inner join "library".borrowed_books bb
on b.id = bb.book_id
where bb.status = 'borrowed';


select r.name, count(*) as countOfBooks 
from "library".borrowed_books bb
inner join "library".readers r
on bb.reader_id = r.id
group by r.name


select r.name 
from "library".borrowed_books bb
inner join "library".readers r
on bb.reader_id = r.id
group by r.name
having count(*) > 2


select b.title, b.author, b.published_year, b.genre, bb.reader_id, bb.borrow_date, bb.return_date, bb.status
from "library".books b
left join "library".borrowed_books bb
on b.id = bb.book_id
where b.genre = 'Novel'