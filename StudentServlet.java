package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

// CONTROLLER: request techukuni, DAO tho matladi, sarigga VIEW (JSP) ki pampistundi
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "new":
                    req.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("student", dao.findById(id));
                    req.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/students");
                    break;
                default:
                    req.setAttribute("students", dao.findAll());
                    req.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");
        try {
            if (idParam == null || idParam.isEmpty()) {
                dao.insert(new Student(name, email, course));
            } else {
                dao.update(new Student(Integer.parseInt(idParam), name, email, course));
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
