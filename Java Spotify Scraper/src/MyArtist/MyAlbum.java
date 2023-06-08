package MyArtist;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    private final Date releaseDate = null;

    /** String array containing the tracks of this album, ordered in the same way they appear
     * in Spotify. */
    private final String[] tracks = null;

    /** This album's title.*/
    private final String title = "";

    // TODO: implement constructor
    protected MyAlbum(){
        int x =2;
    }

    /** Returns a String array with the tracks of this album. */
    public String[] getTracks(){
        return tracks;
    }
}
