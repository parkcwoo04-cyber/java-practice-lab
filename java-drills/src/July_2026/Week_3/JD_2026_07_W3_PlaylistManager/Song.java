package src.July_2026.Week_3.JD_2026_07_W3_PlaylistManager;

public class Song {
    private String title;
    private String artist;
    private int duration;
    private MusicGenre genre;

    public Song(String title, String artist, int duration, MusicGenre genre) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public String songDisplay() {
        String song = "";
        song += "[" + genre + "]";
        song += " " + title;
        song += " - " + artist;
        song += " (" + duration/60 + ":" + duration%60 + ")";
        return song;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
}
