package com.mycompany.projectsdl;

public class QueueNode {

    // Menyimpan data lagu pada node
    private Song data;

    // Menyimpan referensi ke node berikutnya
    private QueueNode next;

    // Konstruktor untuk membuat node baru berisi lagu
    public QueueNode(Song data) {
        this.data = data;
        this.next = null;
    }

    // Mengambil data lagu yang tersimpan pada node
    public Song getData() {
        return data;
    }

    // Mengambil node berikutnya
    public QueueNode getNext() {
        return next;
    }

    // Mengatur node berikutnya
    public void setNext(QueueNode n) {
        this.next = n;
    }
}