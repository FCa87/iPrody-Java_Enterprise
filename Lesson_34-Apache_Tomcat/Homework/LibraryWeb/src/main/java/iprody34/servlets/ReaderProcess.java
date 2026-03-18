package iprody34.servlets;

import com.google.gson.Gson;
import iprody34.libraryjdbc.LibraryAPI;
import iprody34.libraryjdbc.Reader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/readers")
public class ReaderProcess extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setStatus(200);
        resp.addHeader("content-type", "text/html");
        try(PrintWriter printWriter = resp.getWriter()){
            printWriter.println("<h1>Hello from readerprocess</h1>");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().equals("application/json")){
            resp.sendError(415, "Only json body expected!");
            return;
        }
        Gson gson = new Gson();
        Reader addedReader = gson.fromJson(req.getReader(), Reader.class);
        addedReader = LibraryAPI.addReader(addedReader);
        resp.setStatus(201);
        resp.setHeader("Content-type", "application/json");
        try(PrintWriter printWriter = resp.getWriter()){
            printWriter.println("Following reader added:");
            printWriter.println(gson.toJson(addedReader));
        }
    }
    
}
