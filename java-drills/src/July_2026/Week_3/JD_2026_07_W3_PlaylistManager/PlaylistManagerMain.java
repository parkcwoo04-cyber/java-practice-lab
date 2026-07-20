package src.July_2026.Week_3.JD_2026_07_W3_PlaylistManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaylistManagerMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist();

        playlist.loadPlaylist();

        boolean run = true;
        while (run) {
            int menu = menuOption(scanner);
            switch (menu) {
                case 1: // Add song
                    System.out.println("Enter title:");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    System.out.println("Enter artist");
                    String artist = scanner.nextLine();
                    System.out.println("Enter duration:");
                    int duration = scanner.nextInt();
                    MusicGenre genre = genreOption(scanner);

                    playlist.addSong(title, artist, duration, genre);
                    break;

                case 2: // View All Song
                    playlist.viewAllSongs();
                    System.out.println();
                    break;

                case 3: // Search Song
                    System.out.println("Search Keyword:");
                    scanner.nextLine();
                    String keyword = scanner.nextLine();
                    playlist.searchSongByKeyword(keyword);
                    break;

                case 4: // remove song
                    System.out.println("Enter Keyword:");
                    scanner.nextLine();
                    String keyword2 = scanner.nextLine();
                    Song removeSong = playlist.removeSongByKeyword(keyword2, scanner);
                    if (removeSong == null) {
                        break;
                    }

                    playlist.removeSong(removeSong);
                    break;

                case 5: // view summary
                    playlist.printSummary();
                    break;

                case 6: // save playlist
                    playlist.savePlaylist();
                    break;

                case 0: // Exit
                    System.out.println("Bye!");
                    run = false;
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private static int menuOption(Scanner scanner) {
        System.out.println("1. Add Song");
        System.out.println("2. View All Songs");
        System.out.println("3. Search Song");
        System.out.println("4. Remove Song");
        System.out.println("5. View Summary");
        System.out.println("6. Save Playlist");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    private static MusicGenre genreOption(Scanner scanner) {
        System.out.println("1. Pop");
        System.out.println("2. Rock");
        System.out.println("3. Hip Hop");
        System.out.println("4. RnB");
        System.out.println("5. Jazz");
        System.out.println("6. Classical");
        System.out.println("7. Electronic");
        System.out.println("8. Other");

        int choice = scanner.nextInt();

        MusicGenre genre = null;

        if (choice == 1) {
            genre = MusicGenre.POP;
        } else if (choice == 2) {
            genre = MusicGenre.ROCK;
        } else if (choice == 3) {
            genre = MusicGenre.HIP_HOP;
        } else if (choice == 4) {
            genre = MusicGenre.RNB;
        } else if (choice == 5) {
            genre = MusicGenre.JAZZ;
        } else if (choice == 6) {
            genre = MusicGenre.CLASSICAL;
        } else if (choice == 7) {
            genre = MusicGenre.ELECTRONIC;
        } else if (choice == 8) {
            genre = MusicGenre.OTHER;
        }

        return genre;
    }
}
