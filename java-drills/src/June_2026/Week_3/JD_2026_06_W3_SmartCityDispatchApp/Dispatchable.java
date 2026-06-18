package src.June_2026.Week_3.JD_2026_06_W3_SmartCityDispatchApp;

public interface Dispatchable {
    void assign(Incident incident);

    void response(Incident incident);

    void completeMission(Incident incident);
}
