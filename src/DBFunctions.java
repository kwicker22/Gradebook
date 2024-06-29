import java.sql.*;
import java.util.*;


public class DBFunctions {
    // COURSE TABLE  ///
    Connection conn = DBConnections.getConnection();
    public int createCourse(Connection conn, Course course) throws SQLException {
        String sql = "INSERT INTO course (course_name, teacher_name) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getTeacherName());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt("course_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    // STUDENT TABLE //
    public void makeClassList(Connection conn, Students student){
        String sql = "INSERT INTO students (course_id, studentname) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql) ;
            stmt.setInt(1, student.getCourse().getCourse_ID());
            stmt.setString(2, student.getStudentName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ASSIGNMENT TABLE//
    public void makeAssignment(Connection conn, Assignments assignment){
        String sql = "INSERT INTO assignments (course_id,assignmentName) VALUES (?, ?)";
        try {
             PreparedStatement stmt = conn.prepareStatement(sql) ;
            stmt.setInt(1, assignment.getCourse().getCourse_ID());
            stmt.setString(2, assignment.getAssignmentName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAssignment(Connection conn, Assignments assignment) {
        String sql = "UPDATE assignments SET assignmentname = ?, course_id = ? WHERE assignment_id = ?";
        try {
             PreparedStatement stmt = conn.prepareStatement(sql) ;
            stmt.setString(1, assignment.getAssignmentName());
            stmt.setInt(2, assignment.getCourse().getCourse_ID());
            stmt.setInt(3, assignment.getAssignment_ID());


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List <String> viewAssignments(Connection conn, int course_id ) throws SQLException {
            List<String> assignments = new ArrayList<String>();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try{
//                 String sql = "SELECT *  From assignments WHERE course_id = ?";
                 String sql = "SELECT * FROM assignments a JOIN course b ON a.course_id = b.course_id  WHERE a.course_id = ?";
                 stmt = conn.prepareStatement(sql);
                 stmt.setInt(1, course_id);
                 rs = stmt.executeQuery() ;
                while (rs.next()) {
                    String assignmentname = rs.getString("assignmentname");
                    int assignment_id = rs.getInt("assignment_id");
                    String course_info = String.format("%s: %s", assignment_id, assignmentname);



                    assignments.add(course_info);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return assignments;

        }
    public void deleteAssignment(Connection conn, int assignment_id) throws SQLException {
        String sql = "DELETE FROM assignments WHERE assignment_id = ?";
        try{
             PreparedStatement stmt = conn.prepareStatement(sql) ;
            stmt.setInt(1, assignment_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        // Student list for grade entry //
     public List <Students> studentList(Connection conn, int course_id){
         List<Students> students = new ArrayList<Students>();
         PreparedStatement stmt = null;
         ResultSet rs = null;

         try{
//                 String sql = "SELECT *  From assignments WHERE course_id = ?";
             String sql = "SELECT a.studentname, a.student_id, a.course_id, b.course_id, b.course_name, b.teacher_name FROM students a JOIN course b ON a.course_id = b.course_id  WHERE a.course_id = ?";
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, course_id);
             rs = stmt.executeQuery() ;
             while (rs.next()) {
                 String studentname = rs.getString("studentname");
                 int studentiD = rs.getInt("student_id");
                 int courseiD = rs.getInt("course_id");
                 String coursename = rs.getString("course_name");
                 String teachername = rs.getString("teacher_name");
                 Course course = new Course(course_id,coursename, teachername);
                 Students student = new Students(course,studentiD,studentname);





                 students.add(student);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return students;

        }
        public void insertGrades(Grade grade){
            String sql = "INSERT INTO grade (assignment_id , student_id, grade) VALUES (?, ?, ?);";
            try (Connection conn = DBConnections.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, grade.getAssignment().getAssignment_ID());
                stmt.setInt(2, grade.getStudent().getStudent_ID());
                stmt.setInt(3,grade.getGrade());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Grade already entered");
//                e.printStackTrace();
            }
        }
    public List <String> gradeList(Connection conn, int student_id){
        List<String> grades = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
//                 String sql = "SELECT *  From assignments WHERE course_id = ?";
            String sql = "SELECT g.grade, a.assignmentname " +
                    "FROM grade g " +
                    "JOIN students s ON g.student_id = s.student_id " +
                    "JOIN assignments a ON g.assignment_id = a.assignment_id " +
                    "WHERE g.student_id = ?";
            ;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            rs = stmt.executeQuery() ;
            while (rs.next()) {
                int gradeScore = rs.getInt("grade");
                String assignmentname= rs.getString("assignmentname");
                String course_info = String.format("%s: %s", assignmentname, gradeScore);





                grades.add(course_info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;

    }
    public List <String> viewStudents(Connection conn, int course_id ) throws SQLException {
        List<String> students = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
//                 String sql = "SELECT *  From assignments WHERE course_id = ?";
            String sql = "SELECT * FROM students a JOIN course b ON a.course_id = b.course_id  WHERE a.course_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            rs = stmt.executeQuery() ;
            while (rs.next()) {
                String studentname = rs.getString("studentname");
                int student_id = rs.getInt("student_id");
                String course_info = String.format("%s: %s", student_id, studentname);



                students.add(course_info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;

    }




}


