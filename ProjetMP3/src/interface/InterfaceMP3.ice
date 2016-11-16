module ArchDistrib {

    class Tag {
        string name;
    };

    sequence<float> DataSeq;
    sequence<Tag> TagSeq;

    class Song {
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
        DataSeq playSong(int id);

        void addTagSong(int id, string name);
        void removeTagSong(int id, string name);

        void shutdown();
    };
};
