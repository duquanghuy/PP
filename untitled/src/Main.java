import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String code;
    private int gender; // 0 for male, 1 for female
    private float grade;

    public Student(String name, int age, String email, String phone, String code, int gender, float grade) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.code = code;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public float getGrade() { return grade; }

    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, email='%s', phone='%s', code='%s', gender=%s, grade=%.2f}",
                name, age, email, phone, code, (gender == 0 ? "Male" : "Female"), grade);
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayStudents(); break;
                case 3: removeStudentByCode(); break;
                case 4: sortStudentsByGrade(); break;
                case 5: searchStudentByCodeOrName(); break;
                case 6: filterStudentsByGrade(); break;
                case 7: exitProgram(); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add new student");
        System.out.println("2. Display all students");
        System.out.println("3. Remove student by code");
        System.out.println("4. Sort students by descending grade");
        System.out.println("5. Find student by code or name");
        System.out.println("6. Find students with grade >= x");
        System.out.println("7. Exit");
    }

    private static void addStudent() {
        System.out.println("Enter student information:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Code: ");
        String code = scanner.nextLine();

        System.out.print("Gender (0 for Male, 1 for Female): ");
        int gender = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Grade: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        studentList.add(new Student(name, age, email, phone, code, gender, grade));
        System.out.println("Student added successfully.");
    }

    private static void displayStudents() {
        System.out.println("Student List:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private static void removeStudentByCode() {
        System.out.print("Enter student code to remove: ");
        String code = scanner.nextLine();
        boolean removed = studentList.removeIf(student -> student.getCode().equals(code));
        if (removed) {
            System.out.println("Student with code " + code + " removed.");
        } else {
            System.out.println("No student found with code " + code + ".");
        }
    }

    private static void sortStudentsByGrade() {
        studentList.sort(Comparator.comparing(Student::getGrade).reversed());
        System.out.println("Students sorted by descending grade.");
    }

    private static void searchStudentByCodeOrName() {
        System.out.print("Enter student code or name to find: ");
        String keyword = scanner.nextLine();
        Student foundStudent = studentList.stream()
                .filter(student -> student.getCode().equalsIgnoreCase(keyword) || student.getName().equalsIgnoreCase(keyword))
                .findFirst()
                .orElse(null);

        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void filterStudentsByGrade() {
        System.out.print("Enter grade threshold: ");
        float gradeThreshold = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        System.out.println("Students with grade >= " + gradeThreshold + ":");
        studentList.stream()
                .filter(student -> student.getGrade() >= gradeThreshold)
                .forEach(System.out::println);
    }

    private static void exitProgram() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
