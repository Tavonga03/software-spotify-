# Pembagian Tugas Kelompok 2

## Identitas Kelompok

Mata kuliah: Struktur Data

Studi kasus: Kasus 2 - Simulator Smart Playlist Aplikasi Streaming Musik

Jenis aplikasi: Console-Based Application (CLI) Java

## Anggota Kelompok

| No | Nama | Branch | Tanggung Jawab Utama |
| --- | --- | ---  | --- |
| 1 | Tavonga John Junior Gwenhure |  `tavonga` | Pembuatan data dasar `Lagu`, struktur pembungkus `NodeLagu`, dan penyusunan draf Laporan Bab 1. |
| 2 | Gilbert Gaffin Pramudya | `gilbert-gaffin` | Implementasi fitur navigasi player (`nextSong` & `previousSong`) serta pelacakan lagu aktif. |
| 3 | Gilberth Untung |  `gilberth-untung` | Implementasi antrean prioritas "Up Next" menggunakan konsep Queue berbasis referensi manual. |
| 4 | Arnoldus Yoseph Freibedemenz Lawe |  `arnoldus` | Implementasi algoritma pengurutan (*Sorting*) manual untuk data lagu berdasarkan tahun rilis. |
| 5 | Stefanus Yiko | `stefanus` | Implementasi pencarian (*Searching*) judul lagu, rancangan CLI Menu, dan integrasi akhir sistem. |

## Ringkasan Kasus

Aplikasi ini adalah simulator pemutar musik pintar yang mengelola daftar lagu utama menggunakan konsep **Node-Based Doubly Linked List** buatan mandiri agar pengguna bisa melakukan navigasi dua arah (tombol *Next* dan *Previous*) secara lancar.

Selain playlist utama, sistem menyediakan antrean khusus **"Up Next"** menggunakan konsep **Custom Queue (FIFO)** berbasis pointer. Jika antrean "Up Next" berisi lagu, sistem wajib mendahulukan dan memutar habis seluruh lagu di antrean tersebut terlebih dahulu sebelum otomatis kembali melanjutkan pemutaran daftar lagu di playlist utama.

Sistem juga dilengkapi dengan fitur pengurutan perpustakaan lagu berdasarkan tahun rilis serta pencarian lagu berdasarkan kata kunci judul menggunakan algoritma yang dibuat secara mandiri dari nol tanpa bantuan *Java Collection Framework* (`java.util.*`).

## Rancangan Pembagian Kerja

### Tavonga John Junior Gwenhure — Data Lagu & Struktur Node Dasar

Branch: `tavonga`

Tugas:

* Membuat class `Lagu` yang menampung atribut privat: `judul`, `artis`, dan `tahunRilis`.
* Membuat class `NodeLagu` sebagai fondasi utama penyimpanan dinamis yang menyediakan pointer objek manual (`NodeLagu next` dan `NodeLagu prev`).
* Menyediakan kumpulan dummy data lagu (minimal 20-30 data) yang akan di-*hardcode* untuk keperluan pengujian sistem.
* Menyusun dokumen Bab 1 (Deskripsi Kasus & Kebutuhan Fungsional).

Target commit:

* `docs: menambahkan pendahuluan dan spesifikasi fungsional`
* `feat: rancangan awal class lagu dan nodelagu manual`

### Gilbert Gaffin Pramudya — Sistem Navigasi Next & Previous

Branch: `gilbert-gaffin`

Tugas:

* Membuat class `Playlist` bertipe **Doubly Linked List** manual (memiliki pointer `head`, `tail`, dan `current`).
* Mengimplementasikan method `tambahLagu(Lagu lagu)` tanpa menggunakan `ArrayList` atau `LinkedList` bawaan Java.
* Mengimplementasikan fungsi navigasi penunjuk lagu: `nextSong()` (berpindah ke `current.next`) dan `previousSong()` (berpindah ke `current.prev`).
* Menangani validasi pointer agar sistem tidak menghasilkan *NullPointerException* saat berada di ujung list (awal/akhir playlist).

Target commit:

* `feat: implementasi playlist berbasis doubly linked list`
* `feat: menambahkan fungsi navigasi nextSong dan previousSong`

### Gilberth Untung — Sistem Antrean “Up Next”

Branch: `gilberth-untung`

Tugas:

* Membuat class `UpNextQueue` berbasis struktur pointer manual dengan penanda `front` dan `rear` (menerapkan prinsip FIFO).
* Mengimplementasikan method `enqueueSong(Lagu lagu)` untuk menambahkan lagu interupsi ke ekor antrean.
* Mengimplementasikan method `playFromQueue()` / `dequeueSong()` untuk mengambil lagu dari kepala antrean saat akan diputar.
* Menyediakan method pembantu `isEmpty()` untuk mendeteksi apakah antrean prioritas sudah kosong atau belum.

Target commit:

* `feat: membuat custom queue manual untuk up next`
* `feat: implementasi operasi enqueue dan dequeue manual`

### Arnoldus Yoseph Freibedemenz Lawe — Sorting Lagu

Branch: `arnoldus`

Tugas:

* Membuat class `Sorting` untuk melakukan pengurutan koleksi lagu berdasarkan atribut tahun rilis.
* Mengodekan algoritma pengurutan secara mandiri (misal: *Bubble Sort* atau *Selection Sort*) yang dimodifikasi untuk menukar data pada *Linked List*.
* **Dilarang keras** menggunakan fungsi instan seperti `Arrays.sort()` atau `Collections.sort()`.
* Menyusun Bab 3.3 mengenai Analisis Kompleksitas Waktu (Big-O Notation) khusus untuk bagian pengurutan.

Target commit:

* `feat: implementasi algoritma bubble/selection sort manual`
* `docs: menambahkan analisis big-o untuk komponen sorting`

### Stefanus Yiko — Searching Lagu & Integrasi Program

Branch: `stefanus`

Tugas:

* Membuat class `Searching` menggunakan algoritma **Linear Search** manual untuk mencocokkan kata kunci judul lagu dengan data di dalam list.
* Membangun struktur menu interaktif berbasis Command Line Interface (CLI) menggunakan perulangan di dalam `Main.java`.
* Menggabungkan seluruh modul komponen dan menyusun logika interupsi: tombol *Next* harus menguras isi `UpNextQueue` terlebih dahulu sebelum memajukan pointer `current` milik `Playlist`.
* Melakukan testing keseluruhan (*System Testing*) dan menyiapkan tangkapan layar output untuk laporan.

Target commit:

* `feat: implementasi linear search manual untuk judul lagu`
* `feat: mengintegrasikan skenario menu utama cli dan alur interupsi`

---

## Aturan Branch dan Pengumpulan

1. Setiap anggota wajib membuat branch lokal baru dari branch `main` sesuai penamaan di atas.
2. Seluruh pengerjaan **TIDAK BOLEH** mengimpor komponen dari paket `java.util.*` (kecuali `java.util.Scanner` khusus untuk input CLI di kelas Main).
3. Kelas dasar `Lagu.java` dan `NodeLagu.java` dari branch `tavonga` harus digabungkan (*merge*) ke `main` paling awal agar anggota lain memiliki acuan tipe data yang seragam.
4. Branch `stefanus` digabungkan paling terakhir karena bertindak sebagai integrator seluruh kelas objek dan menu utama aplikasi.
5. Sebelum melakukan *Pull Request* atau *Merge*, pastikan program dapat dikompilasi dengan sukses dan bebas dari *Memory Leak*.

---

## Checklist Kesesuaian Tugas

| Kriteria | Penanggung Jawab Utama | Status |
| --- | --- | --- |
| Larangan Java Collection Framework (`java.util.*`) | Semua Anggota | Belum dikerjakan |
| Pembuatan Node-based Doubly Linked List manual | Gilbert Gaffin, Gilbert | Belum dikerjakan |
| Pembuatan Node-based Queue manual | Gilberth Untung | Belum dikerjakan |
| Logika interupsi navigasi (Up Next vs Playlist Utama) | Stefanus Yiko, Gilberth Untung | Belum dikerjakan |
| Algoritma Sorting mandiri pada Node-Based List | Arnoldus | Belum dikerjakan |
| Algoritma Searching mandiri pada Node-Based List | Stefanus Yiko | Belum dikerjakan |
| Pengujian sistem dengan minimal 20-30 data fiktif | Stefanus Yiko, Tavonga | Belum dikerjakan |
| Dokumen Laporan Akhir & Analisis Big-O | Tavonga, Arnoldus | Belum dikerjakan |

---

## Usulan Struktur Class

Struktur berkas kode sumber yang wajib dibangun di dalam folder proyek (`src/`):

```text
src/
  Main.java           <- Menu utama CLI & Logika kontrol interupsi pemutaran
  Lagu.java           <- Model data lagu (Atribut privat, Constructor, Getter/Setter)
  NodeLagu.java       <- Objek pembungkus data lagu dengan pointer next dan prev
  Playlist.java       <- Implementasi manual Doubly Linked List & fungsi navigasi
  UpNextQueue.java    <- Implementasi manual Queue berbasis node untuk antrean
  Sorting.java        <- Kelas penyedia fungsi algoritma pengurutan tahun rilis
  Searching.java      <- Kelas penyedia fungsi algoritma pencarian judul lagu

```
