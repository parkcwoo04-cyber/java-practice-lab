package src.July_2026.Week_4.JD_2026_07_W4_RepairWorkshop;

import java.io.*;
import java.util.ArrayList;

public class RepairJobStorage {
    Workshop workshop;
    ArrayList<RepairJob> repairJobs;
    final String fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/July_2026/Week_4/JD_2026_07_W4_RepairWorkshop/RepairJobStorageFile.txt";

    public RepairJobStorage(Workshop workshop) {
        this.workshop = workshop;
        repairJobs = workshop.getRepairJobs();
    }

    private String jobToLineConvert(RepairJob repairJob) {
        return repairJob.getJobId() + "," + repairJob.getCustomerName() + "," +repairJob.getDeviceName() + "," +
                repairJob.getUrgency() + "," + repairJob.getStatus() + "," + repairJob.finalCharge();
    }

    public void saveRepairJob() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (RepairJob repairJob : repairJobs) {
                bw.write(jobToLineConvert(repairJob));
                bw.newLine();
            }
            System.out.println("saved " + repairJobs.size() + " jobs.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RepairJob lineToJobConvert(String line) throws InvalidWorkshopOperationException {
        String[] parts = line.split(",");
        String jobId = parts[0];
        String customerName = parts[1];
        String deviceName = parts[2];
        String urgency = parts[3];
        String status = parts[4];
        String finalCharge = parts[5];

        RepairJob repairJob = new RepairJob(jobId, customerName, deviceName);
        repairJob.setUrgency(urgency);
        repairJob.setStatus(JobStatus.valueOf(status));
        repairJob.setFinalCharge(Integer.parseInt(finalCharge));

        return repairJob;
    }

    public void loadRepairJob() throws InvalidWorkshopOperationException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                RepairJob job = lineToJobConvert(line);
                job.printJobDetails();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
