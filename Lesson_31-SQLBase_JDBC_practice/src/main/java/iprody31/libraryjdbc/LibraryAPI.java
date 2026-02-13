package iprody31.libraryjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface LibraryAPI {

    String url = "jdbc:postgresql://localhost:5432/MyDB";
    String user = "postgres";
    String password = "admin";

    static Book addBook(Book book) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

            String insertQuery = "INSERT INTO library.books (title, author, published_year, genre) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, book.getTitle());
            insertStmt.setString(2, book.getAuthor());
            insertStmt.setInt(3, book.getPublished_year());
            insertStmt.setString(4, book.getGenre());

            if (insertStmt.executeUpdate() > 0) {
                ResultSet rs = insertStmt.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Соединение закрыто.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return book;
    }

    static OccupiedBook setStatus(OccupiedBook occupiedBook, Status status){
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE library.borrowed_books SET status = ?, return_date = ? WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
                updateStmt.setString(1, status.status);
                updateStmt.setDate(2, new Date(System.currentTimeMillis()));
                updateStmt.setInt(3, occupiedBook.getId());
                if (updateStmt.executeUpdate() > 0) {
                    try(ResultSet rs = updateStmt.getGeneratedKeys()){
                        if (rs.next()) {
                            occupiedBook.setStatus(rs.getString("status").compareTo(Status.BORROWED.status) == 0 ? Status.BORROWED : Status.RETURNED);
                            occupiedBook.setReturn_date(rs.getDate("return_date"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return occupiedBook;
    }

    static Reader addReader(Reader reader) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO library.readers (name, email, phone) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, reader.getName());
                insertStmt.setString(2, reader.getEmail());
                insertStmt.setString(3, reader.getPhone());
                if (insertStmt.executeUpdate() > 0) {
                    try(ResultSet rs = insertStmt.getGeneratedKeys()){
                        if (rs.next()) {
                            reader.setId(rs.getInt("id"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return reader;
    }
    
    static OccupiedBook addOccupiedBook(OccupiedBook occupiedBook) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO library.borrowed_books (book_id, reader_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setInt(1, occupiedBook.getBook_id());
                insertStmt.setInt(2, occupiedBook.getReader_id());
                insertStmt.setDate(3, occupiedBook.getBorrow_date());
                insertStmt.setDate(4, occupiedBook.getReturn_date());
                insertStmt.setString(5, occupiedBook.getStatus().status);
                if (insertStmt.executeUpdate() > 0) {
                    try(ResultSet rs = insertStmt.getGeneratedKeys()){
                        if (rs.next()) {
                            occupiedBook.setId(rs.getInt("id"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return occupiedBook;
    }

    static List<OccupiedBook> occupiedBooks(){
        ArrayList<OccupiedBook> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM library.borrowed_books where status = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(query)) {
                selectStmt.setString(1, Status.BORROWED.status);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    while (rs.next()) {
                        result.add(new OccupiedBook(rs.getInt("id"), rs.getInt("book_id"), rs.getInt("reader_id"), rs.getDate("borrow_date"), rs.getDate("return_date"), Status.BORROWED));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return result;
    }

    static Reader updateReader(Reader reader){
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE library.readers SET name = ?, email = ?, phone = ? WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
                updateStmt.setString(1, reader.getName());
                updateStmt.setString(2, reader.getEmail());
                updateStmt.setString(3, reader.getPhone());
                updateStmt.setInt(4, reader.getId());
                if (updateStmt.executeUpdate() > 0) {
                    try(ResultSet rs = updateStmt.getGeneratedKeys()){
                        if (rs.next()){
                            reader.setName(rs.getString("name"));
                            reader.setEmail(rs.getString("email"));
                            reader.setPhone(rs.getString("phone"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return reader;
    }     

    static List<Book> booksWithStatus(Status status){
        ArrayList<Book> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT b.id, b.title, b.author, b.published_year, b.genre FROM library.books b INNER JOIN library.borrowed_books bb ON b.id = bb.book_id WHERE status = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(query)) {
                selectStmt.setString(1, status.status);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    while (rs.next()) {
                        result.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("published_year"), rs.getString("genre")));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return result;
    }

    static List<Book> booksNotReturnedAfter(Date date){
        ArrayList<Book> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT b.id, b.title, b.author, b.published_year, b.genre FROM library.books b INNER JOIN library.borrowed_books bb ON b.id = bb.book_id WHERE bb.status = ? AND bb.borrow_date > ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(query)) {
                selectStmt.setString(1, Status.BORROWED.status);
                selectStmt.setDate(2, date);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    while (rs.next()) {
                        result.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("published_year"), rs.getString("genre")));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Ошибка при подключении к базе данных!");
            ex.printStackTrace();
        }
        return result;
    }

}
