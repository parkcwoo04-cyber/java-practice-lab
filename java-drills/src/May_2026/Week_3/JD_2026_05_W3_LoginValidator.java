package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD_2026_05_W3_LoginValidator

Class Name: LoginValidator

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 22 minutes

Written by: Chanwoo Park

Problem
Check whether a username and password meet the login requirements.

Requirements
- Create username and password String variables.
- Check for empty username or password values.
- Compare the entered values with the correct login information.
- Print the correct message based on the result.

Learning Objective
Practice String comparison, conditional statements, and boolean logic in Java.
*/

public class JD_2026_05_W3_LoginValidator {
    public static void main(String[] args) {

        String username = "javaStudent";
        String password = "Code1234";

        String correctUsername = "javaStudent";
        String correctPassword = "Code1234";

        if (username.equals(" ")) { //verifying whether username is empty
            System.out.println("Username cannot be empty.");
        } else if (!username.equals(correctUsername)) { //when username is wrong
            System.out.println("Invalid username.");
        } else if (username.equals(correctUsername)) {  //if username matches goes to next step.
            if (password.equals(" ")) {
              System.out.println("Password cannot be empty.");
            } else if (!password.equals(correctPassword)) {
                System.out.println("Invalid password.");
            } else if (password.equals(correctPassword)) {
                System.out.println("Login successful.");
            }
        }
    }
}
