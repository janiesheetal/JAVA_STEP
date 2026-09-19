import java.util.Arrays;

public class Playlist {
    private String[] songs;
    private int songCount;

    public Playlist(int maximumSongs) {
        songs = new String[maximumSongs];
        songCount = 0;
    }

    public void addSong(String song) {
        if (songCount < songs.length) {
            songs[songCount] = song;
            songCount++;
        }
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return songCount;
    }
}