package src.June_2026.Week_3.JD_2026_06_W3_LockerExceptionSimulator;

public class LockerSystem {
    PackageLocker[] lockers;

    public LockerSystem(PackageLocker[] lockers) {
        this.lockers = lockers;
    }

    public void processPickupRequest(String request) throws InvalidLockerAccessException {
        System.out.println("Processing request: " + request);

        String[] requestLines = request.split(":");
        int lockerNumber = Integer.parseInt(requestLines[0]);
        String code = requestLines[1];
        boolean matched = false;

        for (PackageLocker locker : lockers) {
            if (lockerNumber == locker.getLockerNumber()) {
                if (locker.matchesCode(code)) {
                    if (!locker.isPickedUp()) {
                        System.out.println("Pickup Successful: " + locker.getOwnerName() + " received package from locker " + lockerNumber + ".");
                        locker.markPickedUp();
                    } else {
                        throw new InvalidLockerAccessException("Error: Package in locker " + lockerNumber + " was already picked up.");
                    }
                } else {
                    throw new InvalidLockerAccessException("Error: Invalid pickup code for locker " + lockerNumber + ".");
                }
                matched = true;
            }
        }

        if (!matched) {
            throw new ArrayIndexOutOfBoundsException("Error: Locker number is out of range.");
        }
    }
}
