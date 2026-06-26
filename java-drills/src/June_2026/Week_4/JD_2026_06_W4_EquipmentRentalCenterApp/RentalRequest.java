package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

import src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp.InvalidRequestException;

public class RentalRequest {
    private String rentalID;
    private String studentName;
    private RequestType requestType;
    private int numberOfRentalDays;
    private RequestStatus requestStatus;
    private boolean isLongTermRental;

    public RentalRequest(String rentalId, String studentName, RequestType requestType, int numberOfRentalDays) {
        this.rentalID = rentalId;
        this.studentName = studentName;
        this.requestType = requestType;
        this.numberOfRentalDays = numberOfRentalDays;
        this.requestStatus = RequestStatus.PENDING;
        this.isLongTermRental = false;
    }

    public void markReturned() {
        this.requestStatus = RequestStatus.RENTED;
    }

    public void showRequest() {
        if (this.isLongTermRental) {
            System.out.print("[Long Term Rental]");
        }
        System.out.printf("[%s] %s | %s | %s | Rental Period: %d\n", requestStatus, rentalID, studentName, requestType, numberOfRentalDays);
    }

    public boolean validateRequest() throws InvalidRequestException {
        boolean valid = true;

        if (this.rentalID == null || this.rentalID.equals("")) {
            valid = false;
            throw new InvalidRequestException("Request Rejected: Rental Request ID cannot be empty.");
        } else if (this.studentName == null || this.studentName.equals("")) {
            valid = false;
            throw new InvalidRequestException("Request Rejected: Student Name cannot be empty.");
        } else if (this.requestType == null) {
            valid = false;
            throw new InvalidRequestException("Request Rejected: Request Type is not valid.");
        } else if (this.numberOfRentalDays <= 0) {
            valid = false;
            throw new InvalidRequestException("Request Rejected: Number of Rental Days must be greater than 0.");
        } else if (this.numberOfRentalDays > 30) {
            this.isLongTermRental = true;
        }
        return valid;
    }

    public String getRentalId() {
        return rentalID;
    }

    public boolean isLongTermRental() {
        return isLongTermRental;
    }

    public void setRentalId(String rentalId) {
        this.rentalID = rentalId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public int getNumberOfRentalDays() {
        return numberOfRentalDays;
    }

    public void setNumberOfRentalDays(int numberOfRentalDays) {
        this.numberOfRentalDays = numberOfRentalDays;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
