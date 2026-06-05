package src.June_2026.Week_1.JD_2026_06_W1_CinemaSeatPlan;

public class SeatRequest {
    private String customerName;
    private int row;
    private int col;

    public SeatRequest(String customerName, int row, int col) {
        this.customerName = customerName;
        this.row = row;
        this.col = col;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
