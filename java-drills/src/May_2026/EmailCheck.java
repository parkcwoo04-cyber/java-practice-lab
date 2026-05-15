package may_2026;

/*
ReadMe

Class Name: EmailCheck

Difficulty: Level 1 — Guided Basic

Estimated Time: 10–15 minutes

Actual Time Taken:

Written by: Chanwoo Park

Problem
Create a program that checks simple information about an email address.

Requirements
- Store an email address in a String variable.
- Print the length of the email.
- Print whether the email contains "@".
- Print whether the email starts with "admin".

Learning Objective
Practice using basic String methods to analyze text data.
*/

public class EmailCheck {
    public static void main(String[] args) {

        String email = "parkcwoo04admin@gmail.com";

        System.out.println("Email length: " + email.length());
        System.out.println("Contains @: " + email.contains("@"));
        System.out.println("Starts with admin: " + email.startsWith("admin"));

    }
}
