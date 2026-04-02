package iprody34.servlets;

import com.google.gson.Gson;
import iprody34.libraryjdbc.LibraryAPI;
import iprody34.libraryjdbc.OccupiedBook;
import iprody34.libraryjdbc.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;


@WebServlet("/borrow")
public class BorrowedBooksProcess extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String input = req.getParameter("reader_id");
        if (input.isBlank()){
            resp.sendError(400, "Empty parametr. Expected example: \"reader_id\"=5");
            return;
        }
        int reader_id;
        try{
            reader_id = Integer.parseInt(input);
        }catch (Exception ex){
            resp.sendError(400, "Wrong parametr. Expected example: \"reader_id\"=5");
            return;
        }
        
        List<OccupiedBook> occupiedBooks = LibraryAPI.occupiedBooks();
        resp.setStatus(200);
        resp.addHeader("content-type", "text/html");
        try(PrintWriter printWriter = resp.getWriter()){
            if (occupiedBooks.isEmpty()){
                printWriter.println("<h1>There is no borrowed book behind this reader</h1>");
            }else{
                printWriter.print("<h1>This reader has the following borrowed books:</h1>");
                printWriter.print("<br>");
                occupiedBooks.forEach( x -> {
                    if (x.getReader_id() == reader_id){
                        printWriter.println(x);
                        printWriter.print("<br>");
                    }
                });
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().equals("application/json")){
            resp.sendError(415, "Only json body expected!");
            return;
        }
        Gson gson = new Gson();
        OccupiedBook addedOccupiedBook = gson.fromJson(req.getReader(), OccupiedBook.class);
        addedOccupiedBook = LibraryAPI.addOccupiedBook(addedOccupiedBook);
        resp.setStatus(201);
        resp.setHeader("Content-type", "application/json");
        try(PrintWriter printWriter = resp.getWriter()){
            printWriter.println("Following book added:");
            printWriter.println(gson.toJson(addedOccupiedBook));
        }
    }
    
}
