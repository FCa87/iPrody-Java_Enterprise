package iPrody;

import iPrody.dao.*;
import iPrody.model.Book;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    static void main() {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        AuthorDao authors = context.getBean(AuthorDaoImpl.class);
        BookDao books = context.getBean(BookDaoImpl.class);

        //var author1 = new Author(2, "Mark Twan Second", "Not USA");

        /*var book1 = new Book("Title1", 1700, 1);
        var book2 = new Book("Title2", 1800, 1);
        var book3 = new Book("Title3", 1900, 1);

        books.create(book1);
        books.create(book2);
        books.create(book3);
        book3.setId(3);
        book3.setAuthorId(2);
        books.update(book3);
        books.create(book1);
        books.delete(4);*/

        System.out.println(books.findById(3));

        var booksRes = books.findAll();
        for (var book : booksRes){
            System.out.println(book);
        }

        /*List<Book> booksAdd = List.of(
            new Book("Title4", 1950, 1),
            new Book("Title5", 1960, 1),
            new Book("Title6", 1970, 1)
        );

        System.out.println("Amount of saves books is " + books.saveAll(booksAdd));
        books.findAll().forEach(System.out::println);*/


        context.close();

    }
}
