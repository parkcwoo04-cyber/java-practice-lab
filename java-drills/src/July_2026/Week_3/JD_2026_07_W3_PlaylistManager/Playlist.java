package src.July_2026.Week_3.JD_2026_07_W3_PlaylistManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Playlist {
    private ArrayList<Song> playlist;
    private final String filename = "java-drills/src/July_2026/Week_3/JD_2026_07_W3_PlaylistManager/Playlist.txt";

    public Playlist(){
        playlist  = new ArrayList<>();
    }

    public void addSong(String title, String artist, int duration, MusicGenre genre){
        try {
            Song song = new Song(title, artist, duration, genre);
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("Error: Title cannot be empty.");
            } else if (artist == null || artist.isBlank()) {
                throw new IllegalArgumentException("Error: Artist cannot be empty.");
            } else if (duration <= 0) {
                throw new IllegalArgumentException("Error: Duration should be greater than 0.");
            } else if (genre == null) {
                throw new IllegalArgumentException("Error: Invalid genre.");
            } else {
                playlist.add(song);
                System.out.println(title + " has been added.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAllSongs(){
        int i = 1;
        System.out.println("=== Playlist ===");
        for (Song song : playlist){
            System.out.print(i + ". ");
            System.out.print(song.songDisplay());
            System.out.println();
            i++;
        }
    }

    private ArrayList<Song> searchByKeyword(String keyword){
        ArrayList<Song> songs = new ArrayList<>();
        for (Song song : playlist){
            if (song.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                songs.add(song);
            }
        }
        return songs;
    }

    public void searchSongByKeyword(String keyword){
        ArrayList<Song> songs = searchByKeyword(keyword);
        int i = 1;

        if (songs.isEmpty()){
            System.out.println("No songs found.");
            return;
        }

        for (Song song : songs){
            System.out.print(i + ". ");
            System.out.print(song.songDisplay());
            System.out.println();
        }
        System.out.println();
    }

    public Song removeSongByKeyword(String keyword, Scanner scanner) {
        ArrayList<Song> songs = searchByKeyword(keyword);
        Song removeSong = null;
        int i = 1;
        if (songs.isEmpty()){
            System.out.println("No songs found.");
            return null;
        }

        for (Song song : songs){
            System.out.print(i + ". ");
            System.out.print(song.songDisplay());
            System.out.println();
        }
        int choice = scanner.nextInt();
        removeSong = songs.get(choice - 1);
        return removeSong;
    }

    public void removeSong(Song song) {
        playlist.remove(song);
        System.out.println(song.getTitle() + " has been removed.");
    }

    private int songCount() {
        return playlist.size();
    }

    private int totalDuration() {
        int total = 0;
        for (Song song : playlist){
            total += song.getDuration();
        }
        return total;
    }

    private Song longestDuration() {
        Song longestDuration = playlist.get(0);
        for (Song song : playlist){
            if (song.getDuration() > longestDuration.getDuration()){
                longestDuration = song;
            }
        }
        return longestDuration;
    }

    private void countByGenre() {
        int pop = 0;
        int rock = 0;
        int hipHop = 0;
        int rnb = 0;
        int jazz = 0;
        int classical = 0;
        int electronic = 0;
        int other = 0;
        for (Song song : playlist){
            if (song.getGenre() == MusicGenre.POP){
                pop++;
            } else if (song.getGenre() == MusicGenre.ROCK){
                rock++;
            } else if (song.getGenre() == MusicGenre.HIP_HOP){
                hipHop++;
            }  else if (song.getGenre() == MusicGenre.RNB){
                rnb++;
            } else if (song.getGenre() == MusicGenre.JAZZ) {
                jazz++;
            } else if (song.getGenre() == MusicGenre.CLASSICAL) {
                classical++;
            } else if (song.getGenre() == MusicGenre.ELECTRONIC) {
                electronic++;
            } else if (song.getGenre() == MusicGenre.OTHER) {
                other++;
            }
        }
        System.out.println("Genre Count");
        System.out.println("POP: " + pop);
        System.out.println("ROCK: " + rock);
        System.out.println("HIP HOP: " + hipHop);
        System.out.println("RNB: " + rnb);
        System.out.println("JAZZ: " + jazz);
        System.out.println("CLASSICAL: " + classical);
        System.out.println("ELECTRONIC: " + electronic);
        System.out.println("OTHER: " + other);
        System.out.println();
    }

    public void printSummary() {
        System.out.println("=== Summary ===");
        viewAllSongs();
        System.out.println();
        System.out.println("Total Songs Count: " + songCount());
        System.out.println("Total Duration: " + totalDuration()/60 + ":" + totalDuration()%60);
        System.out.println("Longest Song: " + longestDuration().songDisplay());
        System.out.println();
        countByGenre();
    }

    private String songToLineConvert(Song song) {
        return song.getTitle() + "," + song.getArtist() + "," + song.getDuration() + "," + song.getGenre();
    }

    public void savePlaylist() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Song song : playlist){
                bw.write(songToLineConvert(song));
                bw.newLine();
            }
            System.out.println("Playlist saved successfully.");
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Song convertLineToSong(String line) {
        String[] tokens = line.split(",");
        String title = tokens[0];
        String artist = tokens[1];
        int duration = Integer.parseInt(tokens[2]);
        String genreString = tokens[3];
        MusicGenre genre = MusicGenre.valueOf(genreString);

        return new Song(title, artist, duration, genre);
    }

    public void loadPlaylist() {
        File file = new File(filename);
        System.out.println("Loading playlist ....");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                Song song = convertLineToSong(line);

                if (song != null) {
                    playlist.add(song);
                }
            }
        } catch  (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
