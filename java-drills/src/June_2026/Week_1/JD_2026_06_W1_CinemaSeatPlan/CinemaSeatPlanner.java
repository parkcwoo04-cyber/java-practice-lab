package src.June_2026.Week_1.JD_2026_06_W1_CinemaSeatPlan;

public class CinemaSeatPlanner {
    public static void main(String args[]) {
        Theater theater = new Theater(3, 4);

        System.out.println("Initial Seats");
        theater.printSeats();

        SeatRequest[] requests = {
                new SeatRequest("Mina", 0, 1),
                new SeatRequest("Jisoo", 1, 3),
                new SeatRequest("Daniel", 0, 1),
                new SeatRequest("Noah", 3, 0),
                new SeatRequest("Yuna", 2, 2)
        };

        System.out.println();
        System.out.println("Reservation Result");

        theater.reserveSeats(requests[0]);
        theater.reserveSeats(requests[1]);
        theater.reserveSeats(requests[2]);
        theater.reserveSeats(requests[3]);
        theater.reserveSeats(requests[4]);
        System.out.println();

        System.out.println("Final Seats");
        theater.printSeats();
        System.out.println();

        theater.returnAvailableSeats();
    }
}
