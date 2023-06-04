package Artist;

import se.michaelthelin.spotify.*;
import java.util.*;

/**
 * Artist.Artist class, which represents an individual artist with data scraped using the Spotify
 * Web API project. The class was originally implemented by michelle-mrz on the Spotify Web
 * Scraper project.
*/
public class Artist {

    /**
     * json dictionary containing the artist's information, which is provided by the Spotify Web API.
     */
    // TODO: Determine type on API documentation.
    private final HashMap json;

    /**
     * A final String containing the artist's name.
     */
    private final String name;

    /**
     * A final String (or int) that represents the artist's unique id used by Spotify.
     */
    private final int id;

    /**
     * Albums object that contains an artist's albums and the year they were released, as well
     * the songs on each album.
     */
    private final Albums albums;

    // TODO: Create class that represents dictionary of artist albums to create the field
    // containing this information.

    public Artist(){
        // TODO: Implement this
        json = null;
        name = null;
        id = 0;
        albums = new Albums();
    }






}
