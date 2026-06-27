package com.mycompany.projectsdl;

public class HistoryStack {

    // Array untuk menyimpan riwayat lagu
    private Song[] stack;

    // Indeks elemen teratas stack
    private int top;

    // Kapasitas maksimum stack saat ini
    private int capacity;

    // Kapasitas awal stack
    private static final int DEFAULT_CAPACITY = 50;

    // Faktor penambahan kapasitas saat resize
    private static final int RESIZE_FACTOR = 2;

    // Konstruktor untuk membuat stack kosong
    public HistoryStack() {
        capacity = DEFAULT_CAPACITY;
        stack = new Song[capacity];
        top = -1;
    }

    // Menambahkan lagu ke puncak stack
    public void push(Song s) {
        if (s == null) return;

        if (top == capacity - 1)
            resize();

        stack[++top] = s;
    }

    // Menghapus dan mengambil lagu dari puncak stack
    public Song pop() {
        if (isEmpty()) return null;

        Song removed = stack[top];
        stack[top] = null;
        top--;

        return removed;
    }

    // Melihat lagu pada puncak stack tanpa menghapusnya
    public Song peek() {
        if (isEmpty()) return null;

        return stack[top];
    }

    // Menambah kapasitas stack ketika penuh
    private void resize() {
        int newCapacity = capacity * RESIZE_FACTOR;
        Song[] newStack = new Song[newCapacity];

        for (int i = 0; i <= top; i++) {
            newStack[i] = stack[i];
        }

        stack = newStack;
        capacity = newCapacity;
    }

    // Menampilkan seluruh riwayat lagu dari atas ke bawah
    public void display() {
        if (isEmpty()) {
            System.out.println("  (Riwayat kosong)");
            return;
        }

        System.out.println("  [Puncak -> Bawah]");

        for (int i = top; i >= 0; i--) {
            System.out.printf("  %2d. %s%n",
                    (top - i + 1),
                    stack[i]);
        }
    }

    // Mengecek apakah stack kosong
    public boolean isEmpty() {
        return top == -1;
    }

    // Mengambil jumlah data dalam stack
    public int getSize() {
        return top + 1;
    }

    // Mengambil kapasitas maksimum stack saat ini
    public int getCapacity() {
        return capacity;
    }
}