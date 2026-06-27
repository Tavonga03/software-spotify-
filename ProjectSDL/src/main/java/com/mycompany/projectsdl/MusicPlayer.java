package com.mycompany.projectsdl;

import java.util.Scanner;

public class MusicPlayer {

    private DoublyLinkedList playlist;
    private UpNextQueue upNextQueue;
    private HistoryStack history;

    // Konstruktor untuk menginisialisasi playlist, antrian Up Next, dan riwayat pemutaran
    public MusicPlayer() {
        playlist    = new DoublyLinkedList();
        upNextQueue = new UpNextQueue();
        history     = new HistoryStack();
    }

    // Memuat kumpulan lagu default ke dalam playlist
    public void loadDefaultSongs() {
        playlist.addSong(new Song("Blinding Lights",             "The Weeknd",                         2019));
        playlist.addSong(new Song("Hotel California",            "Eagles",                             1977));
        playlist.addSong(new Song("In the End",                  "Linkin Park",                        2000));
        playlist.addSong(new Song("Billie Jean",                 "Michael Jackson",                    1982));
        playlist.addSong(new Song("Flowers",                     "Miley Cyrus",                        2023));
        playlist.addSong(new Song("Wonderwall",                  "Oasis",                              1995));
        playlist.addSong(new Song("Imagine",                     "John Lennon",                        1971));
        playlist.addSong(new Song("Believer",                    "Imagine Dragons",                    2017));
        playlist.addSong(new Song("Black Hole Sun",              "Soundgarden",                        1994));
        playlist.addSong(new Song("Back in Black",               "AC/DC",                              1980));
        playlist.addSong(new Song("Happier Than Ever",           "Billie Eilish",                      2021));
        playlist.addSong(new Song("Sweet Child O' Mine",         "Guns N' Roses",                      1987));
        playlist.addSong(new Song("Drown",                       "Bring Me the Horizon",               2014));
        playlist.addSong(new Song("Bohemian Rhapsody",           "Queen",                              1975));
        playlist.addSong(new Song("Mr. Brightside",              "The Killers",                        2004));
        playlist.addSong(new Song("Fast Car",                    "Tracy Chapman",                      1988));
        playlist.addSong(new Song("Levitating",                  "Dua Lipa",                           2020));
        playlist.addSong(new Song("Smells Like Teen Spirit",     "Nirvana",                            1991));
        playlist.addSong(new Song("The Pretender",               "Foo Fighters",                       2007));
        playlist.addSong(new Song("Shape of You",                "Ed Sheeran",                         2017));
        playlist.addSong(new Song("Sultans of Swing",            "Dire Straits",                       1978));
        playlist.addSong(new Song("Bring Me to Life",            "Evanescence",                        2003));
        playlist.addSong(new Song("Rolling in the Deep",         "Adele",                              2010));
        playlist.addSong(new Song("Kingslayer",                  "Bring Me the Horizon ft. BABYMETAL", 2020));
        playlist.addSong(new Song("Creep",                       "Radiohead",                          1992));
        playlist.addSong(new Song("Shadow Moses",                "Bring Me the Horizon",               2013));
        playlist.addSong(new Song("Stairway to Heaven",          "Led Zeppelin",                       1971));
        playlist.addSong(new Song("Welcome to the Black Parade", "My Chemical Romance",                2006));
        playlist.addSong(new Song("Do I Wanna Know?",            "Arctic Monkeys",                     2013));
        playlist.addSong(new Song("Chop Suey!",                  "System of a Down",                   2001));
    }

    // Memutar lagu berikutnya dan menyimpan lagu saat ini ke riwayat
    public void playNext() {
        Song currentSong = playlist.getCurrent();
        if (currentSong != null) {
            history.push(currentSong);
        }

        if (!upNextQueue.isEmpty()) {
            Song nextSong = upNextQueue.dequeue();
            System.out.printf("%n  > NOW PLAYING (dari Up Next): %s%n", nextSong);
        } else {
            Song nextSong = playlist.next();
            System.out.printf("%n  > NOW PLAYING: %s%n", nextSong);
        }
    }

    // Memutar kembali lagu sebelumnya dari riwayat pemutaran
    public void playPrevious() {
        if (history.isEmpty()) {
            System.out.println("\n  Tidak ada riwayat pemutaran sebelumnya.");
            return;
        }

        Song prevSong = history.pop();
        syncCurrentToSong(prevSong);
        System.out.printf("%n  < PREVIOUS: %s%n", prevSong);
    }

    // Mencari dan memutar lagu berdasarkan judul
    public void playSongByTitle(String title) {
        Song node = playlist.getHead();
        while (node != null) {
            if (node.getTitle().equalsIgnoreCase(title)) {
                playDirect(node);
                return;
            }
            node = node.next;
        }
        System.out.printf("  Lagu \"%s\" tidak ditemukan di playlist.%n", title);
    }

    // Mencari dan memutar lagu berdasarkan nama artis
    public void playSongByArtist(Scanner sc, String artist) {
        // Hitung jumlah hasil
        int count = 0;
        Song node = playlist.getHead();
        while (node != null) {
            if (node.getArtist().toLowerCase().contains(artist.toLowerCase())) count++;
            node = node.next;
        }

        if (count == 0) {
            System.out.printf("  Tidak ada lagu dari artis \"%s\" di playlist.%n", artist);
            return;
        }

        // Kumpulkan hasil ke array
        Song[] results = new Song[count];
        int i = 0;
        node = playlist.getHead();
        while (node != null) {
            if (node.getArtist().toLowerCase().contains(artist.toLowerCase())) {
                results[i++] = node;
            }
            node = node.next;
        }

        // Jika hanya 1 hasil, langsung putar
        if (count == 1) {
            playDirect(results[0]);
            return;
        }

        // Tampilkan daftar pilihan jika lebih dari 1
        System.out.printf("%n  Ditemukan %d lagu dari \"%s\":%n", count, artist);
        for (int j = 0; j < count; j++) {
            System.out.printf("  [%d] %s%n", (j + 1), results[j]);
        }
        System.out.print("  Pilih nomor lagu: ");

        try {
            int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
            if (idx >= 0 && idx < count) {
                playDirect(results[idx]);
            } else {
                System.out.println("  Nomor tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Input tidak valid.");
        }
    }

    // Mencari dan memutar lagu berdasarkan tahun rilis
    public void playSongByYear(Scanner sc, int year) {
        // Hitung jumlah hasil
        int count = 0;
        Song node = playlist.getHead();
        while (node != null) {
            if (node.getReleaseYear() == year) count++;
            node = node.next;
        }

        if (count == 0) {
            System.out.printf("  Tidak ada lagu dari tahun %d di playlist.%n", year);
            return;
        }

        // Kumpulkan hasil ke array
        Song[] results = new Song[count];
        int i = 0;
        node = playlist.getHead();
        while (node != null) {
            if (node.getReleaseYear() == year) {
                results[i++] = node;
            }
            node = node.next;
        }

        // Jika hanya 1 hasil, langsung putar
        if (count == 1) {
            playDirect(results[0]);
            return;
        }

        // Tampilkan daftar pilihan jika lebih dari 1
        System.out.printf("%n  Ditemukan %d lagu dari tahun %d:%n", count, year);
        for (int j = 0; j < count; j++) {
            System.out.printf("  [%d] %s%n", (j + 1), results[j]);
        }
        System.out.print("  Pilih nomor lagu: ");

        try {
            int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
            if (idx >= 0 && idx < count) {
                playDirect(results[idx]);
            } else {
                System.out.println("  Nomor tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Input tidak valid.");
        }
    }

    // Langsung set lagu aktif dan tampilkan NOW PLAYING
    private void playDirect(Song song) {
        Song currentSong = playlist.getCurrent();
        if (currentSong != null && currentSong != song) {
            history.push(currentSong);
        }
        playlist.setCurrent(song);
        System.out.printf("%n  > NOW PLAYING: %s%n", song);
    }

    // Menyesuaikan current song pada playlist dengan lagu yang dipilih
    private void syncCurrentToSong(Song target) {
        Song node = playlist.getHead();
        while (node != null) {
            if (node.getTitle().equals(target.getTitle())
                    && node.getArtist().equals(target.getArtist())) {
                playlist.setCurrent(node);
                return;
            }
            node = node.next;
        }
    }

    // Menambahkan lagu ke Up Next berdasarkan judul lagu
    public void addToUpNextByTitle(String title) {
        Song node = playlist.getHead();
        while (node != null) {
            if (node.getTitle().equalsIgnoreCase(title)) {
                upNextQueue.enqueue(node);
                return;
            }
            node = node.next;
        }
        System.out.printf("  Lagu \"%s\" tidak ditemukan di playlist.%n", title);
    }

    // Menampilkan lagu yang sedang diputar
    public void displayNowPlaying() {
        Song current = playlist.getCurrent();
        if (current == null) {
            System.out.println("  (Tidak ada lagu yang diputar)");
        } else {
            System.out.printf("  > NOW PLAYING: %s%n", current);
        }
    }

    // Menampilkan seluruh isi playlist utama
    public void showPlaylist() {
        System.out.println("\n  +-----------------------------------------------------+");
        System.out.println("  |                  MAIN PLAYLIST                      |");
        System.out.println("  +-----------------------------------------------------+");
        playlist.displayAll();
    }

    // Menampilkan seluruh isi antrian Up Next
    public void showUpNextQueue() {
        System.out.println("\n  +-----------------------------------------------------+");
        System.out.println("  |                   UP NEXT QUEUE                     |");
        System.out.println("  +-----------------------------------------------------+");
        upNextQueue.display();
    }

    // Menampilkan riwayat lagu yang telah diputar
    public void showHistory() {
        System.out.println("\n  +-----------------------------------------------------+");
        System.out.println("  |              RIWAYAT PEMUTARAN (Stack)              |");
        System.out.println("  +-----------------------------------------------------+");
        history.display();
    }

    // Menampilkan daftar lagu yang telah diurutkan berdasarkan tahun rilis
    public void showLibrary() {
        SortingUtil.displaySortedLibrary(playlist);
    }

    // Mengambil objek playlist
    public DoublyLinkedList getPlaylist() {
        return playlist;
    }

    // Mengambil objek antrian Up Next
    public UpNextQueue getUpNextQueue() {
        return upNextQueue;
    }

    // Mengambil objek riwayat pemutaran
    public HistoryStack getHistory() {
        return history;
    }
}