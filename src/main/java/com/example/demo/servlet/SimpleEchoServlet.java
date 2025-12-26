package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);

        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            response.getWriter().write("Hello, Guest");
        } else {
            response.getWriter().write("Hello, " + name.trim());
        }
    }
}
