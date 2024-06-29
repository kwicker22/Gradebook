import java.sql.SQLException;
import java.util.*;
import java.lang.*;


public class Main {

    public static void main(String[]args) throws SQLException {
        Scanner myObj = new Scanner(System.in);
        DBFunctions gradebook = new DBFunctions();
        System.out.println("Welcome to your GradeBook.");
        System.out.println("Let's get started!");
        System.out.println("[N]ew course or [E]xisting course?");
        String newORold = myObj.nextLine();
        Course course = new Course(0, null, null);
        while (true){
            if (newORold.equalsIgnoreCase("n")){
                System.out.println("Name of Class:");
                String nameOfClass = myObj.nextLine();
                System.out.println("Teacher Name:");
                String nameOfTeacher = myObj.nextLine();
                course = new Course(0, nameOfClass, nameOfTeacher);
                course.course_id = gradebook.createCourse(gradebook.conn, course );
                System.out.println("Welcome to " + nameOfClass+ " you have been assign the id: "+ course.course_id);
                break;
            } else if (newORold.equalsIgnoreCase("e")){
                System.out.println("Please Course ID: ");
                int course_id = myObj.nextInt();
                myObj.nextLine();
                course = new Course(course_id,null,null);
                break;
            }else {
                System.out.println("Invalid Input");
                System.out.println("[N]ew course or [E]xisting course?");
                newORold = myObj.nextLine();
            }
        }




        while (true) {
            System.out.println("You can [Generate] student list,  [Access] all assignments, [Insert] grades for assignments, [View] grades, or [Quit] .");
            String action = myObj.nextLine();
            if (action.equalsIgnoreCase("Generate")) {
                String scanner = " ";
                while (!scanner.equalsIgnoreCase("q")) {
                    System.out.println("Add Students Name( Type [Q] to quit adding names");
                    scanner = myObj.nextLine();
                    if (scanner.equals("q")) {
                        break;
                    } else if (scanner.matches("[a-zA-Z]+")) {
                        Students student = new Students(course, 0, scanner);
                        gradebook.makeClassList(gradebook.conn, student);
                    } else {
                        System.out.println("Add Students Name( Type [Q] to quit adding names)");
                        scanner = myObj.nextLine();
                    }
                }


            } else if (action.equalsIgnoreCase("Access")) {
                System.out.println("[Make] assignment, [Update] assignment, [Delete] assignment or [View] assignments:");
                String input = myObj.nextLine();
                if (input.equalsIgnoreCase("Make")) {
                    System.out.println("Name of Assignment:");
                    String nameofAssignment = myObj.nextLine();
                    Assignments assignment = new Assignments(course.course_id, nameofAssignment, course);
                    gradebook.makeAssignment(gradebook.conn, assignment);


                } else if (input.equalsIgnoreCase("Update")) {
                    List<String> assignment = gradebook.viewAssignments(gradebook.conn, course.course_id);
                    if (!assignment.isEmpty()){
                        System.out.println("Assignments");
                        for (String ass : assignment) {
                            System.out.println(ass);
                        }
                        System.out.println("Enter Assignment ID: ");
                        int assignmentID = myObj.nextInt();
                        myObj.nextLine();
                        System.out.println("Updated Name: ");
                        String updatedName = myObj.nextLine();
                        Assignments assignments = new Assignments(assignmentID, updatedName, course);
                        gradebook.updateAssignment(gradebook.conn, assignments);

                    }
                    else{
                        System.out.println("No assignments have been created yet");
                    }

                } else if (input.equalsIgnoreCase("View")) {
                    List<String> assignment = gradebook.viewAssignments(gradebook.conn, course.course_id);
                    if (assignment.isEmpty()){
                        System.out.println("No Assignments have been created");
                    }
                    else{
                        System.out.println("Assignments");
                        for (String ass : assignment) {
                            System.out.println(ass);
                        }
                    }


                }
            } else if (action.equalsIgnoreCase("Insert")) {
                List<String> assignment = gradebook.viewAssignments(gradebook.conn, course.course_id);
                if (assignment.isEmpty()){
                    System.out.println("No Assignments Found");
                }
                else {
                    System.out.println("Assignments");
                    for (String ass : assignment) {
                        System.out.println(ass);
                    }
                    System.out.println("Select Assignment ID: ");
                    int assignmentID = myObj.nextInt();
                    myObj.nextLine();
                    List<Students> students = gradebook.studentList(gradebook.conn, course.course_id);
                    for (Students stu : students) {
                        System.out.println(stu.studentname);
                        System.out.println("Grade: ");
                        int grade = myObj.nextInt();
                        myObj.nextLine();
                        Assignments assignments = new Assignments(assignmentID, null, course);
                        Students student = new Students(course, stu.student_id, stu.studentname);
                        Grade gradeScore = new Grade(0, assignments, student, grade);
                        gradebook.insertGrades(gradeScore);
                    }


                }


            }
            else if (action.equalsIgnoreCase("Delete")){
                List<String> assignment = gradebook.viewAssignments(gradebook.conn, course.course_id);
                System.out.println("Assignments");
                for (String ass : assignment) {
                    System.out.println(ass);
                }
                System.out.println("Assignment ID: ");
                int assignmentID = myObj.nextInt();
                myObj.nextLine();
                gradebook.deleteAssignment(gradebook.conn, assignmentID);
            }
            else if (action.equalsIgnoreCase("View")){
                List<String> studentListView = gradebook.viewStudents(gradebook.conn, course.course_id);
                if(studentListView.isEmpty()){
                    System.out.println( "No Students to Grade");
                }
                else {
                    for (String stu : studentListView) {
                        System.out.println(stu);
                    }
                    System.out.println( "Select Student ID: ");
                    int student_id = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("All Grades");
                    List<String> gradeList = gradebook.gradeList(gradebook.conn, student_id);
                    for (String gr : gradeList){
                        System.out.println(gr);
                    }
                }



            }
            else if(action.equalsIgnoreCase("quit")){
                break;
            }
            else {
                System.out.println("Did you hit here?");
            }

        }

    }

}
