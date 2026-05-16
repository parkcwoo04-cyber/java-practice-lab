package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD-2026-05-W3-MovieTicketChecker


Class Name: MovieTicketChecker

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 40 minutes

Written by: Chanwoo Park

Problem
Create a movie ticket checking program that determines
whether a user can enter the movie theater and calculates
the final ticket price based on age, membership, and weekend status.

Requirements
- Store age in an int variable.
- Store ticket ownership in a boolean variable.
- Store membership type in a String variable.
- Store weekend status in a boolean variable.
- Allow entry when:
  - age is 18 or older and hasTicket is true
  - OR membership is "VIP"
- Set the base ticket price:
  - Weekday: $12
  - Weekend: $15
- Apply a $5 discount if age is under 13.
- Apply an additional $3 discount for VIP members.
- Final price cannot be negative.
- Print:
  - Entry status
  - Final ticket price
  - VIP benefit message
- Use:
  - if-else if-else
  - logical operators (&&, ||)
  - equalsIgnoreCase()

Learning Objective
Practice combining multiple conditions using logical operators,
performing step-by-step calculations,
and using String comparison correctly in Java.
*/

public class JD_2026_05_W3_MovieTicketChecker {

    public static void main(String[] args) {

        int age = 20;
        boolean hasTicket = true;
        String membership = "VIP";
        boolean weekend = true;
        boolean entryStatus = false;

        if ((age >= 18 && hasTicket) || membership.equals("VIP")) {
            entryStatus = true;
        }
        System.out.println("Entry Allowed: " + entryStatus); //Printing out Entry Allowed:

        int ticketPrice; //declaring int for ticket price
        String vIP = "No VIP Benefits";

        if (weekend) {
            ticketPrice = 15;
        }  else {
            ticketPrice = 12;
        }

        if (age < 13) {
            ticketPrice -= 5;
        } else if (membership.equalsIgnoreCase("VIP")) {
            ticketPrice -= 3;
            vIP = "VIP Benefits Applied";
        }
        System.out.println("Final Price: $" + ticketPrice);
        System.out.println(vIP);
    }
}
