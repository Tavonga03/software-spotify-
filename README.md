# software-spotify-
Smart Playlist Simulator — A Java CLI app simulating a music player using hand-built data structures. Features a manual Doubly Linked List for two-way navigation, a custom FIFO "Up Next" queue, manual sorting by release year, and linear search. No Java Collection Framework used.

Here's the full breakdown for your GitHub description:

---

## What is this project?

A console-based Java CLI application that simulates a smart music streaming playlist. It manages a song library using hand-built data structures — no Java Collection Framework allowed — and supports two-way navigation, a priority interrupt queue, sorting, and searching.

---

## Features implemented

**Doubly linked list playlist** — the main song list is built entirely from scratch using manual `next` and `prev` pointers on each node. This allows the player to navigate forward and backward through songs without needing Java's built-in `LinkedList`.

**"Up Next" interrupt queue** — a separate custom FIFO queue sits alongside the main playlist. When a song is added to "Up Next", it takes priority: the player must fully drain the queue before resuming the main playlist. This mirrors how real streaming apps handle song interruption.

**Two-way navigation** — `nextSong()` and `previousSong()` move the `current` pointer through the doubly linked list, with null-safety checks to prevent crashes at either end of the playlist.

**Manual sorting** — the song library can be sorted by release year using a hand-coded algorithm (Bubble Sort or Selection Sort), modified to work directly on a linked list by swapping node data rather than array indices.

**Linear search** — keyword-based title search implemented manually, walking the linked list node by node without any helper library.

**CLI menu** — an interactive terminal menu built with a loop in `Main.java`, tying together all modules and enforcing the interruption rule: pressing Next always checks the queue first.

**Hardcoded test data** — 20–30 dummy songs are baked in to support system testing without needing a database or file input.

---

## Class structure

| Class | Role |
|---|---|
| `Lagu.java` | Data model — holds `judul`, `artis`, `tahunRilis` with private fields, constructor, and getters/setters |
| `NodeLagu.java` | Node wrapper — stores a `Lagu` object plus manual `next` and `prev` pointers |
| `Playlist.java` | Doubly linked list — manages `head`, `tail`, `current`; implements `tambahLagu()`, `nextSong()`, `previousSong()` |
| `UpNextQueue.java` | Custom FIFO queue — manages `front` and `rear` pointers; implements `enqueueSong()`, `dequeueSong()`, `isEmpty()` |
| `Sorting.java` | Manual sort — sorts the linked list by `tahunRilis` using a from-scratch algorithm |
| `Searching.java` | Manual search — linear search through the list matching a keyword against `judul` |
| `Main.java` | Entry point — CLI loop, user input via `Scanner`, interruption logic coordinating `UpNextQueue` and `Playlist` |

---

## Tech stack

- **Java** (console/CLI, no GUI)
- **No `java.util.*`** except `java.util.Scanner` for CLI input — all lists, queues, sorting, and searching are implemented manually from scratch
