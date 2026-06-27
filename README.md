# Smart Playlist Simulator

A console-based Java CLI application that simulates a smart music streaming playlist manager. All data structures are implemented from scratch — no Java Collection Framework (`java.util.*`) is used except `Scanner` for user input.

---

## Features

- **Two-way playlist navigation** — the main song list is built as a manual Doubly Linked List with `next` and `prev` pointers, allowing the player to move forward and backward through songs.
- **"Up Next" interrupt queue** — a custom FIFO queue that takes priority over the main playlist. When songs are queued, the player drains the queue completely before resuming the main playlist.
- **Manual sorting** — the song library can be sorted by release year using a hand-coded Bubble Sort or Selection Sort algorithm that operates directly on linked list nodes.
- **Linear search** — keyword-based title search that walks the linked list node by node without any helper library.
- **Interactive CLI menu** — a terminal menu built with a loop in `Main.java`, coordinating all modules and enforcing the interruption logic.

---

## Class Structure

| Class | Description |
|---|---|
| `Lagu.java` | Data model for a song — stores `judul`, `artis`, and `tahunRilis` |
| `NodeLagu.java` | Node wrapper — holds a `Lagu` object with manual `next` and `prev` pointers |
| `Playlist.java` | Manual Doubly Linked List — handles song insertion and next/previous navigation |
| `UpNextQueue.java` | Manual FIFO queue — handles enqueue, dequeue, and empty checks |
| `Sorting.java` | Manual sort algorithm — sorts songs by release year |
| `Searching.java` | Manual linear search — finds songs by title keyword |
| `Main.java` | Entry point — CLI loop, user input, and interruption logic |

---

## Tech Stack

- **Language:** Java (Console / CLI)
- **Restriction:** No `java.util.*` (except `Scanner`) — all lists, queues, sorting, and searching are built manually from scratch
