package com.mycompany.projectsdl;

public class Song {

    // Menyimpan judul lagu
    private String title;

    // Menyimpan nama penyanyi atau grup musik
    private String artist;

    // Menyimpan tahun rilis lagu
    private int releaseYear;

    // Referensi ke node berikutnya pada Doubly Linked List
    Song next;

    // Referensi ke node sebelumnya pada Doubly Linked List
    Song prev;

    // Konstruktor untuk membuat objek lagu baru
    public Song(String title, String artist, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.next = null;
        this.prev = null;
    }

    // Mengambil judul lagu
    public String getTitle() {
        return title;
    }

    // Mengambil nama artis atau penyanyi
    public String getArtist() {
        return artist;
    }

    // Mengambil tahun rilis lagu
    public int getReleaseYear() {
        return releaseYear;
    }

    // Mengembalikan informasi lagu dalam bentuk teks
    @Override
    public String toString() {
        return String.format("\"%s\" - %s (%d)", title, artist, releaseYear);
    }
}