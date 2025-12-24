package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleEchoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        
        String name = request.getParameter("name");
        
        PrintWriter out = response.getWriter();
        
        if (name == null || name.trim().isEmpty()) {
            out.print("Hello, Guest");
        } else {
            out.print("Hello, " + name.trim());
        }
        
        out.flush();
    }
}