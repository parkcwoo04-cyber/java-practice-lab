package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TransmissionLogWriter {
    private final String fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/August_2026/Week_1/JD_2026_08_W1_DeepSpaceTransmissionControl/ArchivedTransmissionLog.txt";
    MissionControl control;

    public TransmissionLogWriter(MissionControl control) {
        this.control = control;
    }


    private String logToLineConvert(Transmission transmission) {
        return transmission.getTransmissionID() + "," + transmission.getProbeName() + "," + transmission.getAssignedStationID();
    }

    public void saveFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Transmission transmission : control.getArchivedTransmissionList()) {
                bufferedWriter.write(logToLineConvert(transmission));
                bufferedWriter.newLine();
            }
            System.out.println("Archived Transmission Log File has been saved successfully");
        } catch(IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
