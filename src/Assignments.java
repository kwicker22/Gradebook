public class Assignments {
    private int assignment_id;
    private String assignmentname;
    private Course course;


    public Assignments(int assignment_id, String assignmentname, Course course) {
        this.assignment_id= assignment_id;
        this.assignmentname= assignmentname;
        this.course = course;
    }

    // Getters and setters
    public int getAssignment_ID() { return assignment_id; }
    public void setAssignment_ID(int assignment_ID) { this.assignment_id = assignment_id; }
    public String getAssignmentName() {return assignmentname; }
    public void setAssignmentName(String assignmentName) {this.assignmentname= assignmentname;}
    public Course getCourse(){ return course;}
    public void setCourse(Course course) {this.course = course;}

    @Override
    public String toString() {
        return "Assignments{" +
                "assignmentId=" + assignment_id+
                ", name='" + assignmentname + '\'' +
                ", course=" + course +
                '}';
    }


}
