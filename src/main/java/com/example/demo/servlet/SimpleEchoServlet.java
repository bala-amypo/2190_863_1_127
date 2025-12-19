package com.example.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) name = "Guest";
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello, " + name.trim());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
