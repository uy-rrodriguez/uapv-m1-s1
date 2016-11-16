public class ServeurMP3I extends ArchDistrib._InterfaceMP3Disp {
    public void addSong(String name, String artist, String path) {
        System.out.println("addSong name=" + name + "; artist=" + artist + "; path=" + path);
    }

    public void removeSong(int id) {
        System.out.println("removeSong id=" + id);
    }

    public SongSeq listSongs() {
        System.out.println("listSongs");
    }

    public void searchSongs(String nameRegex, String artistRegex) {
        System.out.println("searchSongs nameRegex=" + nameRegex + "; artistRegex=" + artistRegex);
    }

    public void playSong(int id) {
        System.out.println("playSong id=" + id);
    }

    public void addTagSong(int id, String name) {
        System.out.println("addTagSong id=" + id + "; name=" + name);
    }

    public void removeTagSong(int id, String name) {
        System.out.println("removeTagSong id=" + id + "; name=" + name);
    }

    public void shutdown() {
        System.out.println("shutdown");
    }
}
