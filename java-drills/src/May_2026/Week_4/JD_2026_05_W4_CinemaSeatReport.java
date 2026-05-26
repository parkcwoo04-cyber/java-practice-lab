package src.May_2026.Week_4;
/*
ReadMe

Drill ID: JD_2026_05_W4_CinemaSeatReport

Class Name: CinemaSeatReport

Difficulty: Level 4 — Mini Project

Estimated Time: 35-45 minutes

Actual Time Taken: 42 minutes

Written by: Chanwoo Park

Problem
Create a cinema seat report program using a two-dimensional array and nested loops.

Requirements
- Create a two-dimensional String array named seats.
- Store "O" for available seats and "X" for reserved seats.
- Use nested for loops to print the full seat map.
- Count the total number of available seats.
- Count the total number of reserved seats.
- Count available and reserved seats for each row.
- Find the row with the highest number of reserved seats.
- Print the row report, total report, and most reserved row.
- Use .equals() when comparing seat status values.

Learning Objective
Practice nested loops, two-dimensional arrays, counters, row-based calculations, and conditional logic.
*/
public class JD_2026_05_W4_CinemaSeatReport {
    public static void main(String[] args) {

        String[][] seats = {
                {"O", "X", "O", "X", "O"},
                {"X", "X", "O", "O", "X"},
                {"O", "O", "O", "X", "O"},
                {"X", "O", "X", "X", "O"}
        };

        int totalAvailable = 0;
        int totalReserved = 0;

        int reservedCount = 0;
        int mostReservedCount = 0;
        char mostReservedRow = 'A';

        char row = 'A';

        System.out.println("Seat Map");

        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                String seat = String.valueOf(row) + (j + 1);
                System.out.print(seat + ":" + seats[i][j]);
                System.out.print(" ");

                if(seats[i][j].equals("X")){
                    totalReserved++;
                    reservedCount++;
                }

                if(reservedCount > mostReservedCount){
                    mostReservedCount = reservedCount;
                    mostReservedRow = row;
                }
            }
            System.out.println();
            row++;
            reservedCount = 0;
        }

        totalAvailable = 20 - totalReserved;

        System.out.println("\nRow Report");
        System.out.println("Total Available Seats: " + totalAvailable);
        System.out.println("Total Reserved Seats: " + totalReserved);
        System.out.println("Most Reserved Row: " + mostReservedRow);
        System.out.println("Reserved seats in That Row: " + mostReservedCount);
        System.out.println();
    }
}
