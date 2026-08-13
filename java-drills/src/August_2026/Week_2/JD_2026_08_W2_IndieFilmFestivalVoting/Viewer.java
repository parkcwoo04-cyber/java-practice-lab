package src.August_2026.Week_2.JD_2026_08_W2_IndieFilmFestivalVoting;

public class Viewer {
    private String viewerID;
    private String name;
    private String filmID;
    private boolean hasVoted;

    public Viewer(String viewerID, String name) {
        if (viewerID == null || viewerID.isEmpty()) {
            throw new IllegalArgumentException("Viewer ID cannot be empty");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.viewerID = viewerID;
        this.name = name;
        this.filmID = null;
        this.hasVoted = false;
    }

    public String printInfo() {
        return getViewerID() + " | " + getName();
    }

    public String getViewerID() {
        return viewerID;
    }

    public String getName() {
        return name;
    }

    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        if (filmID == null || filmID.isEmpty()) {
            throw new IllegalArgumentException("Film ID cannot be empty");
        }

        this.filmID = filmID;
    }

    public boolean voted() {
        return hasVoted;
    }

    public void setVoted() {
        hasVoted = true;
    }
}
