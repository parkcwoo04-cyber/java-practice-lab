package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl;

public class TelemetryTransmission extends Transmission {
    private int numberOfTelemetryRecords;

    public TelemetryTransmission(String transmissionId, String probeName, int numberOfTelemetryRecords) throws InvalidTransmissionOperationException {
        super(transmissionId, probeName);
        if (numberOfTelemetryRecords <= 0) {
            throw new InvalidTransmissionOperationException("Error: Number of Telemetry Records should be greater than 0.");
        }

        this.numberOfTelemetryRecords = numberOfTelemetryRecords;
    }

    @Override
    public int getCapacity() {
        return numberOfTelemetryRecords;
    }

    @Override
    public String printTransmissionInfo() {
        return "TELEMETRY | " + super.printTransmissionInfo() + " | Telemetry Records: " + numberOfTelemetryRecords ;
    }

    public int getNumberOfTelemetryRecords() {
        return numberOfTelemetryRecords;
    }
}
