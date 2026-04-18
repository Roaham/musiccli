# MusicCLI v2.0 — Standard Edition

MusicCLI is a technical command-line interface (CLI) multimedia manager developed in Java. The system is designed for the efficient management of local audio libraries, integrating automated metadata extraction, database persistence, and an enriched user interface through ANSI rendering.

---

## 🛠️ Current Implementations

The system follows a modular architecture that separates business logic, data access, and visual representation.

### 1. Core and Metadata Extraction
* **Multimedia Abstraction:** Implementation of an abstract `Multimedia` class that standardizes attributes such as `path`, `title`, `artist`, `album`, `genre`, and `duration`.
* **Audio Processing:** Utilization of the `jaudiotagger` library for automatic metadata extraction from `.mp3` files.
* **Exception Handling:** A hierarchy of custom exceptions (`MetadataExtractionException`, `LoadSongException`) for robust error control during I/O operations.

### 2. Playback and Library Services
* **MusicLibrary:** A service responsible for scanning local directories, indexing songs in a `HashMap` for $O(1)$ complexity lookups, and managing dynamic file loading.
* **Audio Engine:** Integration of `JLayer` (`AdvancedPlayer`) to handle MPEG audio stream reproduction.

### 3. Data Persistence
* **JDBC History:** MySQL database connection for persistent logging of executed commands, including timestamps and activity traceability.

### 4. User Interface (UI)
* **ANSI Rendering:** Use of ANSI escape sequences to provide a professional visual interface with colors, banners, and formatted tables.
* **Asynchronous Animations:** Implementation of `ScheduledExecutorService` to run loading and playback animations in background threads, keeping the CLI responsive.

---

## 🚀 Future Implementations

The following development goals have been identified to expand the platform's capabilities:

* **yt-dlp Integration:** Implementation of an automated download module to fetch audio directly from YouTube.
* **Video Support:** Evolution of the class hierarchy to allow the reproduction of music videos by extending the `Multimedia` class.
* **Database Playlist Management:** Migration of the current list system to a relational model in MySQL, allowing for the creation, saving, and editing of persistent custom playlists.

---

## 📋 System Requirements
* **Java JDK:** 17 or higher.
* **Database:** MySQL Server (configured at `localhost:3306`).
* **Dependencies:** `JLayer`, `jaudiotagger`, `mysql-connector-java`.