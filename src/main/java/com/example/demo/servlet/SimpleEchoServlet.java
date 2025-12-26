package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/echo")
public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/plain");
        
        String name = req.getParameter("name");
        
        // Check if name is null, empty, or only whitespace
        if (name == null || name.trim().isEmpty()) {
            name = "Guest";
        } else {
            name = name.trim();
        }
        
        PrintWriter out = resp.getWriter();
        out.print("Hello, " + name);
        out.flush();
    }
}