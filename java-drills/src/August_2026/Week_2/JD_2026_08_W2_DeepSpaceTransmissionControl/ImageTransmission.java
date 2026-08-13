package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl;

public class ImageTransmission extends Transmission {
    private int dataSize;

    public ImageTransmission(String transmissionID, String probeName, int dataSize) throws InvalidTransmissionOperationException {
        super(transmissionID, probeName);

        if (dataSize <= 0) {
            throw new InvalidTransmissionOperationException("Error: Data size should be greater than 0.");
        }

        this.dataSize = dataSize;
    }

    @Override
    public int getCapacity() {
        return dataSize;
    }

    @Override
    public String printTransmissionInfo() {
        return "IMAGE | " + super.printTransmissionInfo() + " | Data size: " + getDataSize() ;
    }

    public int getDataSize() {
        return dataSize;
    }
}
