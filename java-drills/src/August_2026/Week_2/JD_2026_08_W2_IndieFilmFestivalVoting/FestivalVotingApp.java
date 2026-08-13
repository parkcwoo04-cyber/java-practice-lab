package src.August_2026.Week_2.JD_2026_08_W2_IndieFilmFestivalVoting;

public class FestivalVotingApp {
    public static void main(String[] args) {
        FestivalManager manager = new FestivalManager();
        System.out.println("=== Indie Film Festival Voting ===");
        System.out.println();

        registerFilm(manager, "F-201", "Paper Moonlight", Genre.DRAMA);
        registerFilm(manager, "F-202", "Tiny Giants", Genre.DOCUMENTARY);
        registerFilm(manager, "F-203", "Last Bust Home", Genre.COMEDY);
        registerFilm(manager, "F-204", "Cloud Painter", Genre.ANIMATION);
        System.out.println();

        registerViewer(manager, "V-101", "Mina");
        registerViewer(manager, "V-102", "Daniel");
        registerViewer(manager, "V-103", "Sara");
        registerViewer(manager, "V-104", "Leo");
        System.out.println();

        System.out.println("=== Film List ===");
        printFilmDetails(manager);
        System.out.println();

        System.out.println("=== Voting ===");
        voteFilm(manager,"V-101", "F-201", 9);
        voteFilm(manager,"V-102", "F-203", 15);
        voteFilm(manager,"V-102", "F-202", 8);
        voteFilm(manager,"V-103", "F-201", 7);
        voteFilm(manager,"V-104", "F-204", 10);
        voteFilm(manager,"V-101", "F-202", 9);
        voteFilm(manager,"V-999", "F-202", 15);
        System.out.println();

        System.out.println("=== Vote Results ===");
        printVoteResults(manager);
        System.out.println();

        System.out.println("Top Film: " + manager.getTopFilm());
        System.out.println();

        System.out.println("=== Viewer Voting History ===");
        viewVotingHistory(manager);
    }

    private static void registerFilm(FestivalManager manager, String filmID, String title, Genre genre) {
        try {
            Film film = new Film(filmID, title, genre);
            manager.registerFilm(film);
            System.out.println("Registered film: " + film.printInfo());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registerViewer(FestivalManager manager, String viewerID, String name) {
        try {
            Viewer viewer = new Viewer(viewerID, name);
            manager.registerViewer(viewer);
            System.out.println("Registered viewer: " + viewer.printInfo());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void voteFilm(FestivalManager manager, String viewID, String filmID, int rating) {
        try {
            manager.voteFilm(viewID, filmID, rating);
            System.out.println(viewID + " voted " + rating + " for " + filmID + ".");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printFilmDetails(FestivalManager manager) {
        for (Film film : manager.getFilms()) {
            System.out.println(film.printInfo());
        }
    }

    private static void printVoteResults(FestivalManager manager) {
        for (Film film : manager.getFilms()) {
            System.out.println(film.printVoteResults());
        }
    }

    private static void viewVotingHistory(FestivalManager manager) {
        for (Viewer viewer : manager.getViewers()) {
            System.out.println(viewer.getName() + ": " + viewer.getFilmID());
        }
    }
}
