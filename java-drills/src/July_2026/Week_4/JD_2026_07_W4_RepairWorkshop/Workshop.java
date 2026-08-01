package src.July_2026.Week_4.JD_2026_07_W4_RepairWorkshop;

import java.util.ArrayList;

public class Workshop {
    private ArrayList<RepairJob> repairJobs;

    public Workshop() {
        repairJobs = new ArrayList<>();
    }

    public ArrayList<RepairJob> getRepairJobs() {
        return repairJobs;
    }

    public void setRepairJobs(ArrayList<RepairJob> repairJobs) {
        this.repairJobs = repairJobs;
    }

    public void registerJob(RepairJob job) throws InvalidWorkshopOperationException {
        for (RepairJob repairJob : repairJobs) {
            if (repairJob.getJobId().equalsIgnoreCase(job.getJobId())) {
                throw new InvalidWorkshopOperationException("Rejected: Job already exists - " + job.getJobId());
            }
        }
        repairJobs.add(job);
        System.out.println("Registered: " + job.getJobId() + " | " + job.getDeviceName() + " | " + job.getUrgency());
    }

    public void diagnoseJob(String jobID, String diagnoseDescription, int replacePartsCost) throws InvalidWorkshopOperationException {
        RepairJob repairJob = foundJob(jobID);

        if (repairJob == null) {
            throw new InvalidWorkshopOperationException("Rejected: Job not found - " + jobID);
        }

        if (diagnoseDescription == null || diagnoseDescription.isBlank()) {
            throw new InvalidWorkshopOperationException("Rejected: Diagnose Description cannot be blank.");
        }

        if (replacePartsCost <= 0) {
            throw new InvalidWorkshopOperationException("Rejected: Replace Parts Cost should be greater than 0.");
        }

        System.out.printf("%s diagnosed: Diagnose Description: %s | Replacement Cost: $%d\n", jobID, diagnoseDescription , replacePartsCost);
        repairJob.setDiagnosisDescription(diagnoseDescription);
        repairJob.setReplacePartsCost(replacePartsCost);
        repairJob.setStatus(JobStatus.DIAGNOSED);
    }

    public void repair(String jobID) throws InvalidWorkshopOperationException {
        RepairJob repairJob = foundJob(jobID);
        if (repairJob == null) {
            throw new InvalidWorkshopOperationException("Rejected: Job not found - " + jobID);
        }

        if (repairJob.isDiagnosed()) {
            System.out.println(repairJob.getJobId() + " repair started.");
            repairJob.setStatus(JobStatus.IN_REPAIR);
        } else {
            System.out.println(repairJob.getJobId() + " should be diagnosed.");
        }
    }

    public void completeRepair(String jobID) throws InvalidWorkshopOperationException {
        RepairJob repairJob = foundJob(jobID);
        if (repairJob == null) {
            throw new InvalidWorkshopOperationException("Rejected: Job not found - " + jobID);
        }

        if (repairJob.isInRepair()) {
            System.out.println(repairJob.getJobId() + " repair completed.");
            repairJob.setStatus(JobStatus.COMPLETED);
        } else {
            throw new InvalidWorkshopOperationException("Rejected: Job is not in repair.");
        }
    }

    public void collectRepair(String jobID) throws InvalidWorkshopOperationException {
        RepairJob repairJob = foundJob(jobID);
        if (repairJob == null) {
            throw new InvalidWorkshopOperationException("Rejected: Job not found - " + jobID);
        }

        if (repairJob.isCompleted()) {
            System.out.println(repairJob.getJobId() + " collected. | Final charge: $" + repairJob.finalCharge());
            repairJob.setStatus(JobStatus.COLLECTED);
        } else {
            throw new InvalidWorkshopOperationException("Rejected: Job is not in completed.");
        }
    }

    public void cancelRepair(String jobID) throws InvalidWorkshopOperationException {
        RepairJob repairJob = foundJob(jobID);
        if (repairJob == null) {
            throw new InvalidWorkshopOperationException("Rejected: Job not found - " + jobID);
        }

        System.out.println(repairJob.getJobId() + " cancelled.");
        repairJob.setStatus(JobStatus.CANCELLED);
    }

    public void printCompletedJobs() {
        for (RepairJob repairJob : repairJobs) {
            if (repairJob.isCompleted()) {
                repairJob.printJobDetails();
            }
        }
        System.out.println();
    }

    public void printStatusSummary() {
        int received = 0;
        int diagnosed = 0;
        int inRepair = 0;
        int completed = 0;
        int collected = 0;
        int cancelled = 0;

        for (RepairJob repairJob : repairJobs) {
            if (repairJob.isReceived()) {
                received ++;
            } else if (repairJob.isDiagnosed()) {
                diagnosed ++;
            } else if (repairJob.isInRepair()) {
                inRepair ++;
            } else if (repairJob.isCompleted()) {
                completed ++;
            } else if (repairJob.isCollected()) {
                collected ++;
            } else if (repairJob.isCancelled()) {
                cancelled ++;
            }
        }
        System.out.println("RECEIVED: " + received);
        System.out.println("DIAGNOSED: " + diagnosed);
        System.out.println("IN_REPAIR: " + inRepair);
        System.out.println("COMPLETED: " + completed);
        System.out.println("COLLECTED: " + collected);
        System.out.println("CANCELLED: " + cancelled);
        System.out.println();
    }

    private RepairJob foundJob(String jobID) {
        for (RepairJob repairJob : repairJobs) {
            if (repairJob.getJobId().equalsIgnoreCase(jobID)) {
                return repairJob;
            }
        }
        return null;
    }
}
