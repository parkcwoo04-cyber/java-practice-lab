package src.May_2026.Week_4;
/*
ReadMe

Drill ID: JD_2026_05_W4_LibraryBookManager

Class Name: LibraryBookManager

Difficulty: Level 4 — Mini Project

Estimated Time: 30-40 minutes

Actual Time Taken: 28 minutes

Written by: Chanwoo Park

Problem
Create a simple library book management program using arrays and methods.

Requirements
- Store book titles in a String array.
- Store borrowing status in a boolean array.
- Use separate methods to print, search, borrow, and return books.
- Return the book index if the title is found.
- Use equalsIgnoreCase() when comparing book titles.

Learning Objective
Practice organizing Java code with methods, parameters, return values, arrays, loops, conditional statements, and String comparison.
*/
public class JD_2026_05_W4_LibraryBookManager {
    public static void main(String[] args) {
        String[] books = {"Java Basics", "Clean Code", "Python Start", "Data Structure", "Web Design"};
        boolean[] borrowed = {false, true, false, false, false};

        System.out.println("===== Library Book List =====");
        printBooks(books, borrowed);

        System.out.println();

        String searchTitle = "Clean Code";
        System.out.println("Searching for: " + searchTitle);

        int index = findBookIndex(books, searchTitle);

        if (index == -1){
            System.out.println(searchTitle + " was not found.");
        } else {
            if (borrowed[index]) {
                System.out.println(searchTitle + " is currently borrowed.");
            } else {
                System.out.println(searchTitle + " is available.");
            }
        }

        System.out.println();

        borrowBook(books, borrowed, "Java Basics");
        borrowBook(books, borrowed, "Clean Code");
        returnBook(books, borrowed, "Clean Code");

        System.out.println("===== Updated Book List =====");
        printBooks(books, borrowed);

    }

    public static void printBooks(String[] books, boolean[] borrowed){
        String availability = "";

        for (int i = 0; i < books.length; i++){
            if (borrowed[i] == false){
                availability = "Available";
            } else {
                availability = "Borrowed";
            }
            System.out.println((i+1) + ". " + books[i] + " - " + availability);
        }
    }

    public static int findBookIndex(String[] books, String title){

        int index = 0;

        for (int i = 0; i < books.length; i++){
            if (books[i].equals(title)){
                index = i;
                return index;
            } else {
                index = -1;
            }

        }

        return index;
    }

    public static void borrowBook(String[] books, boolean[] borrowed, String title){
        int index = findBookIndex(books, title);

        System.out.println("Borrowing: " + title);

        if  (index == -1){
            System.out.println(title + " was not found.");
        } else {
            if (borrowed[index]){
                System.out.println(title + " is already borrowed.");
            } else {
                System.out.println("You borrowed " + title + ".");
                borrowed[index] = true;
            }
        }
        System.out.println();
    }

    public static void returnBook(String[] books, boolean[] borrowed, String title){
        int index = findBookIndex(books, title);

        System.out.println("Returning: " + title);

        if (index == -1){
            System.out.println(title + " was not found.");
        } else {
            if (!borrowed[index]){
                System.out.println(title + " is not borrowed.");
            } else {
                System.out.println("You returned " + title + ".");
                borrowed[index] = false;
            }
        }
        System.out.println();
    }
}
