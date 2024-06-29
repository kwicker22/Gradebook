import java.sql.*;
import java.util.*;


public class DBFunctions {
    // COURSE TABLE  ///
    public void createCourse(Course course) {
        String sql = "INSERT INTO course (course_name, teacher_name) VALUES (?, ?)";
        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getTeacherName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // STUDENT TABLE //
    public void makeClassList(Students student) {
        String sql = "INSERT INTO students (course_id, studentname) VALUES (?, ?)";
        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, student.getCourse().getCourse_ID());
            stmt.setString(2, student.getStudentName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ASSIGNMENT TABLE//
    public void makeAssignment(Assignments assignment) {
        String sql = "INSERT INTO assignments (course_id,assignmentName) VALUES (?, ?)";
        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignment.getCourse().getCourse_ID());
            stmt.setString(2, assignment.getAssignmentName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAssignment(Assignments assignment) {
        String sql = "UPDATE assignments SET assignmentname = ?, course_id = ? WHERE assignment_id = ?";
        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, assignment.getAssignmentName());
            stmt.setInt(2, assignment.getCourse().getCourse_ID());
            stmt.setInt(3, assignment.getAssignment_ID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Assignments> viewAssignments() {
        List<Assignments> assignments = new ArrayList<>();
        String sql = """
                SELECT assignments.course_id , assignment_id, assignmentname, course_name, teacher_name
                FROM assignments
                INNER JOIN course ON assignments.course_id = course.course_id""";
        try (Connection conn = DBConnections.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int assignmentId = rs.getInt("assignment_id");
                String assignmentName = rs.getString("assignmentname");
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String teacherName = rs.getString("teacher_name");
                Course course = new Course(courseId, courseName, teacherName);
                Assignments assignment = new Assignments(assignmentId, assignmentName, course);
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;

    }

    public List<Students> insertGrades() {
        List<Students> students = new ArrayList<>();
        String sql = """
                SELECT grades.assignment_id , grades.student_id,  grade_id, grade, assignment.assignment_id, assignmentname, assignment.course_id, student.student_id, studentname, student.course_id,
                FROM assignment
                INNER JOIN grades ON grades.assignment_id = assignment.assignment_id
                FROM students
                Inner JOIN grades ON grades.student_id = student.student_id""";
        try (Connection conn = DBConnections.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int gradeId = rs.getInt("grade_id");
                int studentID = rs.getInt("student_id");
                int assignmentId = rs.getInt("assignment_id");
                int courseId = rs.getInt("course_id");
                int gradeScore = rs.getInt("grade");
                String assignmentName = rs.getString("assignmentname");
                String studentName = rs.getString("studentname");

                Students student = new Students(courseId, studentID, studentName);
                Assignments assignment = new Assignments(assignmentId, assignmentName, course);
                Grade grade = new Grade(gradeId, assignment, student, gradeScore);
                students.add(student.studentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}


