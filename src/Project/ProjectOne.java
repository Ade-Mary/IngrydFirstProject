package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class ProjectOne {

    public void createTable()

    {

        try( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "");
            Statement statement = connection.createStatement())
        {

            statement.execute("CREATE TABLE IF NOT EXISTS Project(name Text NOT NULL, email Text, age Int, location Text,designation Text )");
        }catch (Exception ex)
        {

            System.out.println(ex.getMessage());
        }

    }


    public int populateTable() {
        int count = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "");
             Statement statement = connection.createStatement(); Scanner scanner = new Scanner(System.in)) {

            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO project(name, email, age, location,designation) VALUES(?, ?, ?, ?,?)");


            do {
                System.out.println("Please enter your name: ");
                String name = scanner.nextLine();
               // scanner.nextLine();
                System.out.println("Please enter your email: ");
                String email = scanner.nextLine();
                System.out.println("Please enter your age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter your location: ");
                String location = scanner.nextLine();
                System.out.println("Please enter your designation: ");
                String designation = scanner.nextLine();


                insertStatement.setString(1, name);
                insertStatement.setString(2, email);
                insertStatement.setInt(3, age);
                insertStatement.setString(4, location);
                insertStatement.setString(5,designation);

                insertStatement.execute();

                count++;
                System.out.println("you have " +count);
            } while (count < 10);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static void main(String[] args) {
        ProjectOne projectOne=new ProjectOne();
        projectOne.createTable();
        projectOne.populateTable();

    }
}
