package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleEchoServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        
        String name = req.getParameter("name");
        
        if (name == null || name.trim().isEmpty()) {
            resp.getWriter().write("Hello, Guest");
        } else {
            resp.getWriter().write("Hello, " + name.trim());
        }
    }
}