package com.mycompany.projectsdl;

public class DoublyLinkedList {

    private Song head;
    private Song tail;
    private Song current;
    private int size;

    // Konstruktor untuk membuat playlist kosong
    public DoublyLinkedList() {
        head    = null;
        tail    = null;
        current = null;
        size    = 0;
    }

    // Menambahkan lagu baru ke akhir playlist
    public void addSong(Song s) {
        if (s == null) return;

        if (head == null) {
            head    = s;
            tail    = s;
            current = s;
        } else {
            tail.next = s;
            s.prev    = tail;
            tail      = s;
        }
        s.next = null;
        size++;
    }

    // Berpindah ke lagu berikutnya, kembali ke awal jika sudah di akhir
    public Song next() {
        if (current == null) return null;

        current = (current.next != null) ? current.next : head;
        return current;
    }

    // Berpindah ke lagu sebelumnya, kembali ke akhir jika sudah di awal
    public Song previous() {
        if (current == null) return null;

        current = (current.prev != null) ? current.prev : tail;
        return current;
    }

    // Mengambil lagu yang sedang aktif
    public Song getCurrent() {
        return current;
    }

    // Mengubah lagu yang sedang aktif
    public void setCurrent(Song s) {
        this.current = s;
    }

    // Mengurutkan playlist berdasarkan tahun rilis
    public void sortByYear() {
        if (head == null || head.next == null) return;

        Song[] arr = toArray();
        SortingUtil.insertionSort(arr);

        Song node = head;
        for (int i = 0; i < arr.length; i++) {
            node = replaceNodeData(node, arr[i]);
            node = node.next;
        }

        current = head;
    }

    // Mengganti node target dengan data dari node hasil sorting
    private Song replaceNodeData(Song target, Song src) {
        Song replacement = new Song(src.getTitle(), src.getArtist(), src.getReleaseYear());

        replacement.prev = target.prev;
        replacement.next = target.next;

        if (target.prev != null) target.prev.next = replacement;
        else                     head = replacement;

        if (target.next != null) target.next.prev = replacement;
        else                     tail = replacement;

        if (target == current) current = replacement;

        return replacement;
    }

    // Mengubah seluruh isi linked list menjadi array
    public Song[] toArray() {
        Song[] arr  = new Song[size];
        Song   node = head;

        for (int i = 0; i < size; i++) {
            arr[i] = node;
            node   = node.next;
        }
        return arr;
    }

    // Menampilkan seluruh lagu dalam playlist
    public void displayAll() {
        if (head == null) {
            System.out.println("  (Playlist kosong)");
            return;
        }

        Song node = head;
        int idx = 1;

        while (node != null) {
            String marker = (node == current) ? " ◄ NOW PLAYING" : "";
            System.out.printf("  %2d. %s%s%n", idx++, node, marker);
            node = node.next;
        }
    }

    // Mengecek apakah playlist kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Mengambil jumlah lagu dalam playlist
    public int getSize() {
        return size;
    }

    // Mengambil node pertama playlist
    public Song getHead() {
        return head;
    }

    // Mengambil node terakhir playlist
    public Song getTail() {
        return tail;
    }
}