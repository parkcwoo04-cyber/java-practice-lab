package src.June_2026.Week_1.JD_2026_06_W1_CinemaSeatPlan;

public class Theater {
    String[][] seats;
    private int rows;
    private int cols;

    public Theater(int rows, int cols) {
        seats = new String[rows][cols];
        fillSeats();
    }

    public String[][] getSeats() {
        return seats;
    }

    public void setSeats(String[][] seats) {
        this.seats = seats;
    }

    private void fillSeats() {
        char a = 'A';

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = String.valueOf(a) + (j+1);
            }
            a ++;
        }
    }

    public void printSeats() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void reserveSeats(SeatRequest request) {

        String name = request.getCustomerName();
        int rowIndex = request.getRow();
        int colIndex = request.getCol();

        if (rowIndex < 0 || colIndex < 0 || rowIndex >= seats.length || colIndex >= seats[0].length) {
            System.out.println(name + " failed: invalid seat position");
            return;
        } else if (seats[rowIndex][colIndex].equals("XX")) {
            System.out.println(name + " failed: " + (char) (rowIndex + 1) + (colIndex + 1) + " is already reserved.");
        } else {
            System.out.println(name + " reserved " + seats[rowIndex][colIndex] + ".");
            seats[rowIndex][colIndex] = "XX";
        }
    }

    private int countAvailableSeats() {
        int count = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (!seats[i][j].equals("XX")) {
                    count++;
                }
            }
        }
        return count;
    }

    public void returnAvailableSeats() {
        System.out.println("Available Seats: " + countAvailableSeats());
    }
}
