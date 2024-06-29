public class Course {
    public int course_id;
    public String course_name;
    public String teacher_name;


    public Course(int course_id, String course_name, String teacher_name) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.teacher_name = teacher_name;
    }

    // Getters and setters
    public int getCourse_ID() { return course_id; }
    public void setCourse_ID(int course_ID) { this.course_id = course_id; }
    public String getCourseName() { return course_name; }
    public void setCourseName(String courseName) { this.course_name = course_name; }
    public String getTeacherName() {return teacher_name; }
    public void setTeacherName(String teacherName) {this.teacher_name = teacher_name;}

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + course_id +
                ", courseName='" + course_name +
                ", teacherName='" + teacher_name + '\'' +
                '}';
    }


}
