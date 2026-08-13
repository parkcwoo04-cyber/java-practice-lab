package src.August_2026.Week_2.JD_2026_08_W2_IndieFilmFestivalVoting;

import java.util.ArrayList;

public class FestivalManager {
    private ArrayList<Film> films;
    private ArrayList<Viewer> viewers;

    public FestivalManager() {
        films = new ArrayList<>();
        viewers = new ArrayList<>();
    }

    public void registerFilm(Film film){
        for (Film f : films) {
            if (f.getFilmID().equals(film.getFilmID())) {
                throw new IllegalArgumentException("Error: Duplicate Film ID");
            }
        }
        films.add(film);
    }

    public void registerViewer(Viewer viewer){
        for (Viewer v : viewers) {
            if (v.getViewerID().equals(viewer.getViewerID())) {
                throw new IllegalArgumentException("Error: Duplicate Film ID");
            }
        }
        viewers.add(viewer);
    }

    public void voteFilm(String viewID, String filmID, int rating) {
        boolean foundFilm = false;
        boolean foundViewer = false;

        for (Viewer v : viewers) {
            if (v.getViewerID().equals(viewID)) {
                foundViewer = true;
                if (!v.voted()) {
                    for (Film f : films) {
                        if (f.getFilmID().equals(filmID)) {
                            foundFilm = true;
                            f.addRating(rating);
                            v.setVoted();
                            v.setFilmID(filmID);
                            return;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Error: " + viewID + " already voted");
                }
            }
        }

        if (!foundViewer) {
            throw new IllegalArgumentException("Error: " + viewID + " not found");
        }

        if (!foundFilm) {
            throw new IllegalArgumentException("Error: " + filmID + " not found");
        }
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public ArrayList<Viewer> getViewers() {
        return viewers;
    }

    public String getTopFilm() {
        Film topFilm = films.getFirst();
        for (Film f : films) {
            if (f.averageRating() > topFilm.averageRating()) {
                topFilm = f;
            }
        }

        return topFilm.getTitle();
    }
}
