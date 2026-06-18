package src.June_2026.Week_3.JD_2026_06_W3_SmartCityDispatchApp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class DispatchCenter {
    LinkedHashSet<Incident> incidents = new LinkedHashSet<>();
    ArrayList<ResponseUnit> units = new ArrayList<>();
    LinkedHashMap<Incident, ResponseUnit> incidentAssign = new LinkedHashMap<>();

    public DispatchCenter(LinkedHashSet<Incident> incidents, ArrayList<ResponseUnit> units) {
        this.incidents = incidents;
        this.units = units;
    }

    public void registerResponseUnit(ResponseUnit responseUnit) {
        units.add(responseUnit);
        System.out.println("Unit registered: " + responseUnit.getUnitID() + " " + responseUnit.getUnitName());
    }

    public void showUnitSummary() {
        System.out.println("=== Unit summary ===");
        for (ResponseUnit responseUnit : units) {
            responseUnit.showInfo();
        }
        System.out.println();
    }

    public void reportIncident(Incident incident) {
        try {
            if (incident.getSeverity() <= 5 && incident.getSeverity() >= 1) {
                incidents.add(incident);
                incident.setIncidentStatus(IncidentStatus.REPORTED);
                System.out.println("Accepted: " + incident.getIncidentID() + " " + incident.getIncidentType() + " at " + incident.getLocation());
            } else {
                incident.setIncidentStatus(IncidentStatus.REJECTED);
                incidents.add(incident);
                throw new InvalidIncidentException("Rejected: severity must be between 1 and 5");
            }
        } catch (NullPointerException e) {
            System.out.println("Rejected: All fields must be filled");
        } catch (InvalidIncidentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error");
        }

    }

    public void summaryIncidents() {
        System.out.println("=== Incident Summary ===");
        for (Incident incident : incidents) {
            incident.showInfo();
        }
    }

    public void assignUnit(Incident incident) {
        boolean assigned = false;

        try {
            if(incident.getIncidentStatus() == IncidentStatus.REPORTED) {
                if (incident.getIncidentType().equals(IncidentType.FIRE)) {
                    for (ResponseUnit responseUnit : units) {
                        if (responseUnit instanceof FireUnit) {
                            if (responseUnit.availability) {
                                ((FireUnit) responseUnit).assign(incident);
                                assigned = true;
                                incidentAssign.put(incident, responseUnit);
                                break;
                            }
                        }
                    }
                } else if (incident.getIncidentType().equals(IncidentType.CRIME) || incident.getIncidentType().equals(IncidentType.TRAFFIC)) {
                    for (ResponseUnit responseUnit : units) {
                        if (responseUnit instanceof PoliceUnit) {
                            if (responseUnit.availability) {
                                ((PoliceUnit) responseUnit).assign(incident);
                                assigned = true;
                                incidentAssign.put(incident, responseUnit);
                                break;
                            }
                        }
                    }
                } else if (incident.getIncidentType().equals(IncidentType.MEDICAL)) {
                    for (ResponseUnit responseUnit : units) {
                        if (responseUnit instanceof MedicalUnit) {
                            if (responseUnit.availability) {
                                ((MedicalUnit) responseUnit).assign(incident);
                                assigned = true;
                                incidentAssign.put(incident, responseUnit);
                                break;
                            }
                        }
                    }
                }
            } else if (incident.getIncidentStatus() == IncidentStatus.REJECTED) {
                assigned = true;
                throw new InvalidIncidentException(incident.getIncidentID() + " has been rejected");
            }
        } catch (InvalidIncidentException e) {
            System.out.println(e.getMessage());
        }

        if (!assigned) {
            System.out.println(incident.getIncidentID() + " could not be assigned");
        }
    }

    public void responseIncident() {
        System.out.println("=== Incident Response ===");
        for (Incident incident : incidentAssign.keySet()) {
            ResponseUnit responseUnit = incidentAssign.get(incident);
            System.out.println(incident.getIncidentID() + ": " + incident.getIncidentType());
            Dispatchable dispatchable = (Dispatchable) responseUnit;
            dispatchable.response(incident);
            dispatchable.completeMission(incident);
            System.out.println();
        }
    }

    public void finalIncidentReport() {
        System.out.println("=== Final Incident Report ===");
        for (Incident incident : incidents) {
            incident.showReport();
        }
        System.out.println();
    }

    public void classifyByStatus() {
        int reported = 0;
        int assigned = 0;
        int rejected = 0;
        int resolved = 0;

        System.out.println("=== Classify By Status ===");
        for (Incident incident : incidents) {
            if (incident.getIncidentStatus() == IncidentStatus.REPORTED) {
                reported++;
            } else if (incident.getIncidentStatus() == IncidentStatus.ASSIGNED) {
                assigned++;
            } else if (incident.getIncidentStatus() == IncidentStatus.REJECTED) {
                rejected++;
            } else if (incident.getIncidentStatus() == IncidentStatus.RESOLVED) {
                resolved++;
            }
        }

        System.out.println("Reported incidents: " + reported);
        System.out.println("Assigned incidents: " + assigned);
        System.out.println("Rejected incidents: " + rejected);
        System.out.println("Resolved incidents: " + resolved);
        System.out.println();
    }

    public void countByType() {
        System.out.println("=== Count By Type ===");
        int fire = 0;
        int medical = 0;
        int traffic = 0;
        int crime = 0;

        for (Incident incident : incidents) {
            if (incident.getIncidentType() == IncidentType.FIRE) {
                fire++;
            } else if (incident.getIncidentType() == IncidentType.MEDICAL) {
                medical++;
            } else if (incident.getIncidentType() == IncidentType.TRAFFIC) {
                traffic++;
            } else if (incident.getIncidentType() == IncidentType.CRIME) {
                crime++;
            }
        }

        System.out.println("Fire: " + fire);
        System.out.println("Medical: " + medical);
        System.out.println("Traffic: " + traffic);
        System.out.println("Crime: " + crime);
    }
}
