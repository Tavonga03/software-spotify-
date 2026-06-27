package com.mycompany.projectsdl;

public class UpNextQueue {

    // Node paling depan (tempat penghapusan data)
    private QueueNode front;

    // Node paling belakang (tempat penambahan data)
    private QueueNode rear;

    // Jumlah elemen dalam antrian
    private int size;

    // Konstruktor untuk membuat antrian kosong
    public UpNextQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Menambahkan lagu ke belakang antrian (enqueue)
    public void enqueue(Song s) {
        if (s == null) {
            return;
        }

        QueueNode newNode = new QueueNode(s);

        if (rear == null) {
            // Jika antrian kosong
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;

        System.out.printf("  [UP NEXT] Ditambahkan: %s%n", s);
    }

    // Menghapus dan mengambil lagu dari depan antrian (dequeue)
    public Song dequeue() {
        if (isEmpty()) {
            return null;
        }

        Song removed = front.getData();
        front = front.getNext();

        if (front == null) {
            rear = null;
        }

        size--;
        return removed;
    }

    // Melihat lagu paling depan tanpa menghapusnya
    public Song peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getData();
    }

    // Menampilkan seluruh isi antrian Up Next
    public void display() {
        if (isEmpty()) {
            System.out.println("  (Antrian Up Next kosong)");
            return;
        }

        QueueNode node = front;
        int idx = 1;

        while (node != null) {
            System.out.printf("  %2d. %s%n", idx++, node.getData());
            node = node.getNext();
        }
    }

    // Mengecek apakah antrian kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Mengambil jumlah lagu dalam antrian
    public int getSize() {
        return size;
    }
}