package iprody31.libraryjdbc;

import java.sql.Date;

public class LibraryJDBC {

    // !!! Запускается для пустых и сброшенных таблиц!!!
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        
        System.out.println(LibraryAPI.addBook(new Book("The adventures of Tom Sawyer", "Twain Mark", 1876, "Novel")));
        System.out.println(LibraryAPI.addBook(new Book("Crime and Punishment", "Dostoyevsky Fyodor", 1866, "Novel")));
        System.out.println(LibraryAPI.addBook(new Book("Hamlet", "William Shakespeare", 1601, "Tragedy")));
        System.out.println(LibraryAPI.addBook(new Book("Norwegian Wood", "Murakami Haruki", 1987, "Novel")));
        
        System.out.println(LibraryAPI.addReader(new Reader("Mike", "cool@mail.ru", "+79251268754")));
        System.out.println(LibraryAPI.addReader(new Reader("Sam", "ordinary@mail.ru", "+79857775544")));
        System.out.println(LibraryAPI.addReader(new Reader("Robert", "hot@mail.ru", "+77584632158")));
        System.out.println(LibraryAPI.addReader(new Reader("Yan", "notOrdinary@mail.ru", "+71122334455")));
        System.out.println(LibraryAPI.addReader(new Reader("John", "space@mail.ru", "+73554879525")));
        
        System.out.println(LibraryAPI.addOccupiedBook(new OccupiedBook(1, 3, Date.valueOf("2026-01-03"), Date.valueOf("2026-01-20"), Status.RETURNED)));
        System.out.println(LibraryAPI.addOccupiedBook(new OccupiedBook(4, 2, Date.valueOf("2026-01-14"), null, Status.BORROWED)));
        System.out.println(LibraryAPI.addOccupiedBook(new OccupiedBook(2, 3, Date.valueOf("2026-01-25"), null, Status.BORROWED)));
        System.out.println(LibraryAPI.addOccupiedBook(new OccupiedBook(3, 1, Date.valueOf("2025-12-25"), Date.valueOf("2026-02-02"), Status.RETURNED)));
        
        System.out.println(LibraryAPI.setStatus(new OccupiedBook(3, 2, 3, Date.valueOf("2026-01-25"), null, Status.BORROWED), Status.RETURNED));
        LibraryAPI.occupiedBooks().forEach(System.out::println);
        System.out.println(LibraryAPI.updateReader(new Reader(5, "Gerbert", "ghty@mail.ru", "+71111111111")));
        LibraryAPI.booksWithStatus(Status.RETURNED).forEach(System.out::println);
        LibraryAPI.booksNotReturnedAfter(Date.valueOf("2026-01-13")).forEach(System.out::println);
    }
}
