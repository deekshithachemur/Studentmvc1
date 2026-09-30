package com.example.dao;

import com.example.model.Student;
import com.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO: JDBC code anta ikkade untundi (CRUD operations)
public class StudentDAO {

    public List<Student> findAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, email, course FROM students ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("course")));
            }
        }
        return list;
    }

    public Student findById(int id) throws SQLException {
        String sql = "SELECT id, name, email, course FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("id"), rs.getString("name"),
                            rs.getString("email"), rs.getString("course"));
                }
            }
        }
        return null;
    }

    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getCourse());
            ps.executeUpdate();
        }
    }

    public void update(Student s) throws SQLException {
        String sql = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getCourse());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
