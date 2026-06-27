package datastructure;

import model.SLLNode;
import model.Song;

/**
 * ============================================================
 * STACK - Riwayat Lagu yang Telah Diputar (History)
 * ============================================================
 * Mengimplementasikan struktur data Stack berbasis Singly Linked List
 * menggunakan prinsip LIFO (Last In, First Out).
 *
 * Fungsi utama:
 *   - push()  : Menyimpan lagu yang baru saja diputar ke riwayat
 *   - pop()   : Mengambil lagu terakhir yang diputar (untuk Previous)
 *   - peek()  : Melihat lagu terakhir tanpa menghapusnya
 *
 * Kompleksitas semua operasi: O(1)
 */
public class Stack {
    private SLLNode top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Menambahkan lagu ke atas stack (push).
     * Time Complexity: O(1)
     */
    public void push(Song song) {
        SLLNode newNode = new SLLNode(song);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Mengambil dan menghapus lagu dari atas stack (pop).
     * Digunakan saat pengguna menekan tombol "Previous".
     * Time Complexity: O(1)
     */
    public Song pop() {
        if (isEmpty()) {
            throw new RuntimeException("[Stack] Underflow: Tidak ada riwayat lagu.");
        }
        Song song = top.data;
        top = top.next;
        size--;
        return song;
    }

    /**
     * Melihat lagu paling atas tanpa menghapusnya (peek).
     * Time Complexity: O(1)
     */
    public Song peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    /**
     * Menampilkan seluruh isi riwayat (dari paling baru ke paling lama).
     * Time Complexity: O(n)
     */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("  [Kosong] Belum ada riwayat pemutaran.");
            return;
        }
        SLLNode temp = top;
        int i = 1;
        while (temp != null) {
            System.out.printf("  %d. %s%n", i++, temp.data.toShortString());
            temp = temp.next;
        }
    }

    public boolean isEmpty() { return size == 0; }
    public int getSize() { return size; }
}
