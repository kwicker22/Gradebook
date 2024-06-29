import java.util.*;
import java.lang.*;


public class Main {

    public static void main(String[]args) {
        Scanner myObj = new Scanner(System.in);
        DBFunctions gradebook = new DBFunctions();
        System.out.println("Welcome to your GradeBook.");
        System.out.println("Let's get started!");
        System.out.println("[N]ew course or [E]xisting course?");
        String newORold = myObj.nextLine();
        Course course = new Course(0, null, null);
        if (newORold.equalsIgnoreCase("n")){
            System.out.println("Name of Class:");
            String nameOfClass = myObj.nextLine();
            System.out.println("Teacher Name:");
            String nameOfTeacher = myObj.nextLine();
            course = new Course(0, nameOfClass, nameOfTeacher);
            gradebook.createCourse(course);
        } else if (newORold.equalsIgnoreCase("e")){
            System.out.println("Please Course ID: ");
            int course_id = myObj.nextInt();
            myObj.nextLine();
            course = new Course(course_id,null,null);
        }


        while (true) {
            System.out.println("You can [Generate] student list,  [View] all assignments, [insert] grades for assignments, [Calculate] class average, or [Quit] .");
            String action = myObj.nextLine();
            if (action.equals("Generate")) {
                while (true) {
                    System.out.println("Add Students Name( Type q to quit adding names");
                    String scanner = myObj.nextLine();
                    if (!scanner.equals("q")) {
                        Students student = new Students(course, 0, scanner);
                        gradebook.makeClassList(student);

                    } else {
                        break;
                    }
                }


            }
            else if (action.equals("View")){
                System.out.println("[Make] assignment, [Update] assignment, or [View] assignments:");
                String input = myObj.nextLine();
                if (input.equalsIgnoreCase("Make")){
                    System.out.println("Name of Assignment:");
                    String nameofAssignment = myObj.nextLine();
                    Assignments assignment = new Assignments(0, nameofAssignment, course);
                    gradebook.makeAssignment(assignment);


                }
                else if (input.equalsIgnoreCase("Update")){
                    System.out.println("Enter Assignment ID: ");
                    int assignmentID = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Updated Name: ");
                    String updatedName = myObj.nextLine();
                    Assignments assignments = new Assignments(assignmentID, updatedName, course);
                    gradebook.updateAssignment(assignments);

                } else if (input.equalsIgnoreCase("View")){
                    List<Assignments> assignment  = gradebook.viewAssignments();
                    for (Assignments ass : assignment) {
                        System.out.println(ass);
                    }
                    gradebook.viewAssignments();
                }
            }
//            else if (action.equals("Insert")){
//                gradebook.insertGrades();
//            }
//            else if (action.equals("Calculate")){
//                gradebook.calculateClassAverage();
//            }
//        }
        }
    }

}