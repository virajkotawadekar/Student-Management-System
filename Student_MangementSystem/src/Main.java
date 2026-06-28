import model.Student;
import service.AdminService;
import service.StudentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        AdminService adminService = new AdminService();

        boolean isAdmin = false;

        System.out.println("=== Student Management System ===");
        System.out.print("Login as Admin? (yes/no): ");
        String ans = sc.nextLine();

        if (ans.equalsIgnoreCase("yes")) {
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            if (adminService.login(user, pass)) {
                isAdmin = true;
                System.out.println("Admin login successful!");
            } else {
                System.out.println("Invalid admin credentials. Continuing as normal user.");
            }
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Active Students");
            System.out.println("3. Search Student");
            System.out.println("4. Filter by Course");
            System.out.println("5. Filter by Marks Range");

            if (isAdmin) {
                System.out.println("6. Update Student");
                System.out.println("7. Soft Delete Student");
                System.out.println("8. View Deleted Students");
                System.out.println("9. Restore Student");
                System.out.println("10. Exit");
            } else {
                System.out.println("6. Exit");
            }

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (!isAdmin) {
                if (choice == 6) break;

                switch (choice) {
                    case 1 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Course: ");
                        String course = sc.nextLine();

                        System.out.print("Marks: ");
                        String m = sc.next().replace("%", "");
                        double marks = Double.parseDouble(m);

                        service.addStudent(
                                new Student(id, name, age, course, marks));
                    }

                    case 2 -> service.viewStudents();

                    case 3 -> {
                        System.out.print("ID: ");
                        service.searchStudent(sc.nextInt());
                    }

                    case 4 -> {
                        System.out.print("Course keyword: ");
                        service.filterByCourse(sc.nextLine());
                    }

                    case 5 -> {
                        System.out.print("Min marks: ");
                        double min =
                                Double.parseDouble(sc.next().replace("%", ""));

                        System.out.print("Max marks: ");
                        double max =
                                Double.parseDouble(sc.next().replace("%", ""));

                        service.filterByMarks(min, max);
                    }

                    default -> System.out.println("Invalid Choice!");
                }
            } else {

                switch (choice) {
                    case 1 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Course: ");
                        String course = sc.nextLine();

                        System.out.print("Marks: ");
                        double marks =
                                Double.parseDouble(sc.next().replace("%", ""));

                        service.addStudent(
                                new Student(id, name, age, course, marks));
                    }

                    case 2 -> service.viewStudents();

                    case 3 -> {
                        System.out.print("ID: ");
                        service.searchStudent(sc.nextInt());
                    }

                    case 4 -> {
                        System.out.print("Course keyword: ");
                        service.filterByCourse(sc.nextLine());
                    }

                    case 5 -> {
                        System.out.print("Min marks: ");
                        double min =
                                Double.parseDouble(sc.next().replace("%", ""));

                        System.out.print("Max marks: ");
                        double max =
                                Double.parseDouble(sc.next().replace("%", ""));

                        service.filterByMarks(min, max);
                    }

                    case 6 -> {
                        System.out.print("ID to update: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Course: ");
                        String course = sc.nextLine();

                        System.out.print("Marks: ");
                        double marks =
                                Double.parseDouble(sc.next().replace("%", ""));

                        service.updateStudent(
                                new Student(id, name, age, course, marks));
                    }

                    case 7 -> {
                        System.out.print("ID to soft delete: ");
                        service.softDeleteStudent(sc.nextInt());
                    }

                    case 8 -> service.viewDeletedStudents();

                    case 9 -> {
                        System.out.print("ID to restore: ");
                        service.restoreStudent(sc.nextInt());
                    }

                    case 10 -> {
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> System.out.println("Invalid Choice!");
                }
            }
        }
    }
}