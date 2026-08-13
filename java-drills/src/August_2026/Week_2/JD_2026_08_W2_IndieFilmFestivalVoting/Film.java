package src.August_2026.Week_2.JD_2026_08_W2_IndieFilmFestivalVoting;

import java.util.ArrayList;

public class Film {
    private String filmID;
    private String title;
    private Genre genre;
    private ArrayList<Integer> ratings;
    private int averageRating;

    public Film(String filmID, String title, Genre genre) {
        if (filmID == null || filmID.isEmpty()) {
            throw new IllegalArgumentException("Film ID cannot be empty");
        }

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.filmID = filmID;
        this.title = title;
        this.genre = genre;
        this.ratings = new ArrayList<>();
    }

    public String printInfo() {
        return getFilmID() + " | " + getTitle() + " | " + getGenre();
    }

    public String printVoteResults() {
        if (ratings.isEmpty()) {
            return getFilmID() + " | " + getTitle() + " | Average: 0 | Votes: 0";
        }
        return getFilmID() + " | " + getTitle() + " | Average: " + averageRating() + " | Votes: " + getVotes();
    }

    public String getFilmID() {
        return filmID;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void addRating(int ratings) {
        if (ratings < 0 || ratings > 10) {
            throw new IllegalArgumentException("Ratings must be between 0 and 10");
        }

        this.ratings.add(ratings);
    }

    public int averageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();
    }

    private int getVotes() {
        return this.ratings.size();
    }
}
