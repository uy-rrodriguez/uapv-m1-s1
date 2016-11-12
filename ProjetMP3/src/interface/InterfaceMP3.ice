module ArchDistrib {

    class Tag {
        string name;
    };

    sequence<float> DataSeq;
    sequence<Tag> TagSeq;

    class Song {
        string name;
        string artist;
        DataSeq data;
        TagSeq tags;
    };

    class SongModel extends Song {
        string path;
    };

    interface InterfaceMP3 {
        void printString(string s);
    };
};
