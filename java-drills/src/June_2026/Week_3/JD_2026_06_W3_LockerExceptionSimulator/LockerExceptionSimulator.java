package src.June_2026.Week_3.JD_2026_06_W3_LockerExceptionSimulator;

public class LockerExceptionSimulator {
    public static void main(String[] args) {
        PackageLocker[] lockers = {
                new PackageLocker(0, "Mina", "1234"),
                new PackageLocker(1, "Daniel", "2468"),
                new PackageLocker(2, "Leo", "7777"),
        };

        LockerSystem system = new LockerSystem(lockers);

        String[] requests = {
                "0:1234",
                "one:9999",
                "5:5555",
                "1:0000",
                "2:7777",
                "2:7777"
        };

        for (String request : requests) {
            try {
                system.processPickupRequest(request);
            } catch(NumberFormatException e) {
                System.out.println("Error: Lock number must be numeric.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (InvalidLockerAccessException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error Occurred.");
            } finally {
                System.out.println("Request Completed");
            }
            System.out.println();
        }

        System.out.println("All pickup requests have been processed.");
    }
}
