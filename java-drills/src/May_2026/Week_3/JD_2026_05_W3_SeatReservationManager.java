package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD_2026_05_W3_SeatReservationManager

Class Name: SeatReservationManager

Difficulty: Level 4 — Mini Project

Estimated Time: 35–45 minutes

Actual Time Taken: 47 minutes

Written by: Chanwoo Park

Problem
Create and manage a simple movie theater seat reservation system using a two-dimensional array.

Requirements
- Create seats from A1 to D5 using a two-dimensional array.
- Print the initial seat layout.
- Check whether the target seat exists.
- Check whether the target seat is already reserved.
- Print the reservation result.
- Print the updated seat layout using [X] for reserved seats.

Learning Objective
Practice two-dimensional arrays, nested loops, String comparison, boolean flags, and basic validation logic.
*/

public class JD_2026_05_W3_SeatReservationManager {
    public static void main(String args[]) {

        String[][] seats = new String[4][5];
        String[] reservedSeats = {"A3", "D5"};
        String targetSeat = "C3";

        boolean seatExists = false;
        boolean alreadyReserved = false;

        char a = 'A';

        System.out.println("Initial Seats: ");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = String.valueOf(a) + (j+1);
                System.out.print(seats[i][j] + " ");

                if (targetSeat.equals(seats[i][j])) {
                    seatExists = true;
                }
            }
            a++;
            System.out.println();
        }

        System.out.println("\nTarget Seat: " + targetSeat);

        if (seatExists) {
            for (int i = 0; i < reservedSeats.length; i++) {
                if (reservedSeats[i].equals(targetSeat)) {
                    alreadyReserved = true;
                    System.out.println("Seat already reserved");
                }
            }
            System.out.println("Reservation completed");
        } else {
            System.out.println("Invalid seat");
        }

        System.out.println("\nUpdated seats:");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equals(targetSeat)) {
                    seats[i][j] = "[X]";
                }

                for (int k = 0; k < reservedSeats.length; k++) {
                    if (reservedSeats[k].equals(seats[i][j])) {
                        seats[i][j] = "[X]";
                    }
                }
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }
}
