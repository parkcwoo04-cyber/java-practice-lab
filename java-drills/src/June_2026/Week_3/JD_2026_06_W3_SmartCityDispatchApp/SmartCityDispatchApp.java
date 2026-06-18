package src.June_2026.Week_3.JD_2026_06_W3_SmartCityDispatchApp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class SmartCityDispatchApp {
    public static void main(String[] args) {
        LinkedHashSet<Incident> incidents = new LinkedHashSet<Incident>();
        ArrayList<ResponseUnit> responseUnits = new ArrayList<ResponseUnit>();

        DispatchCenter dispatchCenter =  new DispatchCenter(incidents, responseUnits);

        System.out.println("=== Smart City Dispatch System ===");
        System.out.println();

        System.out.println("=== Response Unit Registration ===");
        dispatchCenter.registerResponseUnit(new FireUnit("F-101", "Central Fire Unit", "Central Fire Station", true));
        dispatchCenter.registerResponseUnit(new PoliceUnit("P-101", "Central Police Unit", "Central Police Station", true));
        dispatchCenter.registerResponseUnit(new PoliceUnit("P-102", "West Police Unit", "West Police Station", true));
        dispatchCenter.registerResponseUnit(new MedicalUnit("M-101", "Central Medical Unit", "Central Hospital", true));
        dispatchCenter.registerResponseUnit(new MedicalUnit("M-102", "North Medical Unit", "North Hospital", true));
        System.out.println();

        dispatchCenter.showUnitSummary();

        System.out.println("=== Incident Reports ===");
        dispatchCenter.reportIncident(new Incident("INC-001", "City Library", IncidentType.FIRE, 4, "Multiple casualties reported"));
        dispatchCenter.reportIncident(new Incident("INC-002", "Central Station", IncidentType.MEDICAL, 2, "Heart disease"));
        dispatchCenter.reportIncident(new Incident("INC-003", "Market Street", IncidentType.CRIME, 4, "No Singularity"));
        dispatchCenter.reportIncident(new Incident("INC-004", "Union Station", IncidentType.MEDICAL, 0, "No Singularity"));
        dispatchCenter.reportIncident(new Incident("INC-005", "City Library", IncidentType.FIRE, 0, "Multiple casualties reported"));
        dispatchCenter.reportIncident(new Incident("INC-006", "River Bridge", IncidentType.TRAFFIC, 4, "Multiple casualties reported"));
        dispatchCenter.reportIncident(new Incident("INC-007", "Old Factory", IncidentType.FIRE, 4, "Multiple casualties reported"));
        System.out.println();

        dispatchCenter.summaryIncidents();

        System.out.println("=== Incident Assignment ===");
        for (Incident incident : incidents) {
            dispatchCenter.assignUnit(incident);
        }
        System.out.println();

        dispatchCenter.responseIncident();

        dispatchCenter.finalIncidentReport();

        dispatchCenter.classifyByStatus();

        dispatchCenter.countByType();
    }
}
