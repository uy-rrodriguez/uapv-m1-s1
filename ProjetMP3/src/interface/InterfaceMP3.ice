module ArchDistrib {

    struct Song {
        int id;
        string name;
        string artist;
        string path;
    };

    sequence<Song> SongSeq;
    sequence<byte> DataSeq;

    interface InterfaceMP3 {
        void addSong(string name, string artist, DataSeq file);
        void removeSong(int id);
        SongSeq listSongs();
        SongSeq searchSongs(string nameRegex, string artistRegex);
        void playSong(int id);
        void stopSong();
    };
};
