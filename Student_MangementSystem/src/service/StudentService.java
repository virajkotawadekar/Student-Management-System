package service;

import model.Student;
import util.DBConnection;

import java.sql.*;

public class StudentService {

    // ADD STUDENT
    public void addStudent(Student s) {
        String sql = "INSERT INTO students (id, name, age, course, marks, is_active) VALUES (?, ?, ?, ?, ?, true)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getCourse());
            ps.setDouble(5, s.getMarks());

            ps.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Student with this ID already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW ACTIVE STUDENTS
    public void viewStudents() {
        String sql = "SELECT * FROM students WHERE is_active=true";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                printStudent(rs);
            }

            if (!found) {
                System.out.println("No active students found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW DELETED STUDENTS (ADMIN)
    public void viewDeletedStudents() {
        String sql = "SELECT * FROM students WHERE is_active=false";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                printStudent(rs);
            }

            if (!found) {
                System.out.println("No deleted students found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SEARCH ACTIVE STUDENT
    public void searchStudent(int id) {
        String sql = "SELECT * FROM students WHERE id=? AND is_active=true";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                printStudent(rs);
            } else {
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE STUDENT (ADMIN)
    public void updateStudent(Student s) {
        String sql = """
                UPDATE students
                SET name=?, age=?, course=?, marks=?
                WHERE id=? AND is_active=true
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getCourse());
            ps.setDouble(4, s.getMarks());
            ps.setInt(5, s.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
                searchStudent(s.getId());
            } else {
                System.out.println("Active student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SOFT DELETE STUDENT (ADMIN)
    public void softDeleteStudent(int id) {
        String sql = "UPDATE students SET is_active=false WHERE id=? AND is_active=true";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                System.out.println("Student soft-deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // RESTORE STUDENT (ADMIN)
    public void restoreStudent(int id) {
        String sql = "UPDATE students SET is_active=true WHERE id=? AND is_active=false";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                System.out.println("Student restored successfully!");
            } else {
                System.out.println("Student not found or already active!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FILTER BY COURSE
    public void filterByCourse(String course) {
        String sql = "SELECT * FROM students WHERE is_active=true AND course LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + course + "%");

            ResultSet rs = ps.executeQuery();
            boolean found = false;

            while (rs.next()) {
                found = true;
                printStudent(rs);
            }

            if (!found) {
                System.out.println("No matching students found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FILTER BY MARKS RANGE
    public void filterByMarks(double min, double max) {
        String sql = "SELECT * FROM students WHERE is_active=true AND marks BETWEEN ? AND ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();
            boolean found = false;

            while (rs.next()) {
                found = true;
                printStudent(rs);
            }

            if (!found) {
                System.out.println("No students found in this range.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // PRINT STUDENT DETAILS
    private void printStudent(ResultSet rs) throws SQLException {
        System.out.println(
                "ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Course: " + rs.getString("course") +
                        ", Marks: " + rs.getDouble("marks")
        );
    }
}