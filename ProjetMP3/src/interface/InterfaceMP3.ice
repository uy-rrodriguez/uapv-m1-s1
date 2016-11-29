module ArchDistrib {

    struct Tag {
        string name;
    };

    sequence<float> DataSeq;
    sequence<Tag> TagSeq;

    struct Song {
        int id;
        string name;
        string artist;
        string path;
        TagSeq tags;
    };

    sequence<Song> SongSeq;

    interface InterfaceMP3 {
        void addSong(string name, string artist, string path);
        void removeSong(int id);
        SongSeq listSongs();
        SongSeq searchSongs(string nameRegex, string artistRegex);
        void playSong(int id);
        void stopSong();

        void addTagSong(int id, string name);
        void removeTagSong(int id, string name);
    };
};
