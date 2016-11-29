import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

import ArchDistrib.Song;
import ArchDistrib.Tag;

public class ServeurMP3I extends ArchDistrib._InterfaceMP3Disp {

    private List<Song> songs;
    private Map<String, List<Song>> mapNameSongs;
    private Map<String, List<Song>> mapArtistSongs;
    private List<int[]> mapsIndexes;

    // Map avec les IPs des clients et le player actuel
    private Map<String, Player> mapClientsPlayers;

    public ServeurMP3I() {
        this.songs = new ArrayList<Song>();
        this.mapNameSongs = new HashMap<String, List<Song>>();
        this.mapArtistSongs = new HashMap<String, List<Song>>();
        this.mapsIndexes = new ArrayList<int[]>();

        this.mapClientsPlayers = new HashMap<String, Player>();
    }

    private String getClientIP(Ice.Current current) {
        String currentStr = current.con.toString();
        String regex = ".*remote address = ([0-9.]+):[0-9]+";
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(currentStr);

        if (m.find())
            return m.group(1);
        else
          return "Inconnu";
    }

    private void log(Ice.Current current, String text, Object... vars) {
        for (Object o : vars) {
            try {
              text = text.replaceFirst("\\?", o.toString());
            }
            catch (Exception ex) {}
        }

        System.out.println("<" + getClientIP(current) + ">\t" + text);
    }

	  public void addSong(String name, String artist, String path, Ice.Current current) {
        log(current, "addSong name=?; artist=?; path=?", name, artist, path);
        Song s = new Song();
        s.name = name;
        s.artist = artist;
        s.path = path;
        s.id = this.songs.size() + 1;

        // Ajout dans la liste de chansons
        this.songs.add(s);
        int indexes[] = new int[2];
        this.mapsIndexes.add(indexes);

        // Ajout dans le map par nom pour simplifier la recherche
        List<Song> l;
        if (this.mapNameSongs.containsKey(name)) {
            l = this.mapNameSongs.get(name);
        }
        else {
            l = new ArrayList<Song>();
            this.mapNameSongs.put(name, l);
        }

        l.add(s);
        indexes[0] = l.size() - 1;

        // Ajout dans le map par artiste pour simplifier la recherche
        if (this.mapArtistSongs.containsKey(name)) {
            l = this.mapArtistSongs.get(name);
        }
        else {
            l = new ArrayList<Song>();
            this.mapArtistSongs.put(name, l);
        }

        l.add(s);
        indexes[1] = l.size() - 1;

    }

    public void removeSong(int id, Ice.Current current) {
        log(current, "removeSong id=?", id);

        if (this.songs.size() >= id && this.songs.get(id-1) != null) {
            // Suppression de la liste de chansons
            Song s = this.songs.get(id-1);
            this.songs.set(id-1, null);

            // Suppression dans la liste auxiliaire qui stocke les indexes dans les map
            int indexes[] = this.mapsIndexes.get(id-1);
            this.mapsIndexes.set(id-1, null);

            // Suppression du map par nom et par artiste
            this.mapNameSongs.get(s.name).remove(indexes[0]);
            this.mapArtistSongs.get(s.artist).remove(indexes[1]);
        }
    }

    public Song[] listSongs(Ice.Current current) {
        log(current, "listSongs");
        List<Song> aux = new ArrayList<Song>();
        for (Song s : this.songs) {
            if (s != null)
                aux.add(s);
        }
        return aux.toArray(new Song[0]);
    }

    public List<Song> searchRegexMap(Map<String, List<Song>> map, String regex) {
        List<Song> results = new ArrayList<Song>();

        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Set<String> keys = map.keySet();
        Iterator<String> ite = keys.iterator();

        while (ite.hasNext()) {
            String candidate = ite.next();
            Matcher m = p.matcher(candidate);
            if (m.matches()) {
                results.addAll(map.get(candidate));
            }
        }

        return results;
    }

    public Song[] searchSongs(String nameRegex, String artistRegex, Ice.Current current) {
        log(current, "searchSongs nameRegex=?; artistRegex=?", nameRegex, artistRegex);

        List<Song> byName = this.searchRegexMap(this.mapNameSongs, nameRegex);
        List<Song> byArtist = this.searchRegexMap(this.mapArtistSongs, artistRegex);
        byName.retainAll(byArtist);

        // On doit ordonner les resultats
        Collections.sort(byName, new Comparator<Song>() {
                @Override
                public int compare(Song s1, Song s2) {
                    return s1.id - s2.id;
                }
            });

        return byName.toArray(new Song[0]);
    }

    public void playSong(int id, Ice.Current current) {
        log(current, "playSong id=?", id);

        // On obtient la chanson a partir de l'id
        if (id <= this.songs.size()) {
            Song s = songs.get(id-1);
            File f = new File(s.path);

            // Si le fichier existe, on va le reproduire
            if(f.exists() && !f.isDirectory()) {
                String ip = getClientIP(current);

                if (this.mapClientsPlayers.containsKey(ip)) {
                    Player actuel = this.mapClientsPlayers.get(ip);
                    if (actuel != null)
                        actuel.stop();

                    this.mapClientsPlayers.remove(ip);
                }

                Player player = new Player();
                this.mapClientsPlayers.put(ip, player);

                String mrl = s.path;
                player.start(mrl, ip);

                return;
            }

            log(current, "Fichier '" + s.path + "' non trouve");
        }

        log(current, "ID de chanson non trouve");
    }

    public void stopSong(Ice.Current current) {
        log(current, "stopSong");

        String ip = getClientIP(current);
        if (this.mapClientsPlayers.containsKey(ip)) {
            Player player = this.mapClientsPlayers.get(ip);
            if (player != null)
                player.stop();

            this.mapClientsPlayers.remove(ip);
        }
    }

    public void addTagSong(int id, String name, Ice.Current current) {
        log(current, "addTagSong id=?; name=?", id, name);
    }

    public void removeTagSong(int id, String name, Ice.Current current) {
        log(current, "removeTagSong id=?; name=?", id, name);
    }
}
