public class Students {
    public Course course;
    public int student_id;
    public String studentname;



    public Students(Course course,int student_id, String studentname) {
        this.student_id= student_id;
        this.studentname= studentname;
        this.course = course;
    }

    // Getters and setters
    public int getStudent_ID() { return student_id; }
    public void setStudent_ID(int student_ID) { this.student_id = student_id; }
    public String getStudentName() {return studentname; }
    public void setStudentName(String studentName) {this.studentname= studentName;}
    public Course getCourse(){ return course;}
    public void setCourse(Course course) {this.course = course;}

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + student_id +
                ", name='" + studentname + '\'' +
                ", course=" + course +
                '}';
    }
}
