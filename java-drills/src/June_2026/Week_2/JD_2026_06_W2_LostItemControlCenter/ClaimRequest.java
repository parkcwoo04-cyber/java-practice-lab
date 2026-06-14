package src.June_2026.Week_2.JD_2026_06_W2_LostItemControlCenter;

public class ClaimRequest {
    String studentName;
    String itemID;
    String description;

    public ClaimRequest(String studentName, String itemID, String description) {
        this.studentName = studentName;
        this.itemID = itemID;
        this.description = description;
    }

    public String getStudentName() {
        return studentName;

    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getItemID() {
        return itemID;
    }
}
