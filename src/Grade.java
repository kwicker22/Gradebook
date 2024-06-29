public class Grade {
    public int grade_id;
    public Assignments assignment;
    public Students student;
    public int grade;


    public Grade(int grade_id, Assignments assignment, Students student, int grade) {
        this.grade_id= grade_id;
        this.assignment= assignment;
        this.student = student;
        this.grade = grade;
    }

    // Getters and setters
    public int getGrade_id() { return grade_id; }
    public void setGrade_ID(int grade_ID) { this.grade_id= grade_id; }
    public Assignments getAssignment() {return assignment; }
    public void setAssignment(Assignments assignment) {this.assignment= assignment;}
    public Students getStudent(){ return student;}
    public void setStudent(Students student) {this.student = student;}

    @Override
    public String toString() {
        return "Grades{" +
                "gradeId=" + grade_id+
                ", Assignment ='" + assignment+ '\'' +
                ", Student =" + student+ '\'' +
                ", Grade =" + grade +
                '}';
    }


}


