package MyArtist;
import java.util.Calendar;
import java.util.List;
import se.michaelthelin.spotify.SpotifyApi;

// TODO: Implement class

/**
 * Protected class that wraps around several of the provided Album classes in the Spotify Web API,
 * which contain useful yet needless features that would make developing the functionality
 * of the entire scraper too complicated.
 */
class MyAlbum{

    /** String containing the id 'id' of this album. */
    private final String id = "";

    /** Release date of this album, stored as Date object. */
    private final Calendar releaseDate = null;

    /** The number of songs on this album, a positive integer less than 30.*/
    private int size;

    /** String array containing the tracks of this album, ordered in the same way they appear
     * in Spotify. */
    private final String[] tracks = null;

    /** This album's title.*/
    private final String title = "";

    // TODO: implement constructor
    protected MyAlbum(String id, SpotifyApi api){
        int x =2;
    }

    /** Returns a String array with the tracks of this album. */
    public String[] getTracks(){
        return tracks;
    }

    /** Returns this album's title.*/
    public String getTitle(){
        return title;
    }

    /** Returns this album's id.*/
    public String getId(){
        return id;
    }

    /** Returns the number of tracks on this album.*/
    public int getSize(){
        return size;
    }
}
