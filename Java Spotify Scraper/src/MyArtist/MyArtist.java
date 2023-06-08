package MyArtist;

import java.util.*;

/**
 * MyArtist class, which represents an individual artist with data scraped using the Spotify
 * Web API project. The class was originally implemented by michelle-mrz on the Spotify Web
 * Scraper project. The class is named like so to avoid confusion with the one provided by
 * the Spotify Web API utility. The decision to make this extra class was for simplicity, as much of
 * what is provided by the aforementioned library is useful yet unnecessary for our purposes.
*/
public class MyArtist {

    //TODO: find a way to minimize number of requests made to the API/ figure out a way
    // to use multiple client ids/secrets to be able to make more requests.

    /**
     * A final String containing the artist's name.
     */
    private final String name;


    /**
     * A final String (or int) that represents the artist's unique id used by Spotify.
     */
    private final String id;

    /**
     * Albums object that contains an artist's albums and the year they were released, as well
     * the songs on each album.
     */
    // TODO: employ APIs capabilities to fetch multiple albums at the same time.
    private MyAlbum[] albums;

    // FIXME: environment variable access.
    private static final String CLIENT_SECRET = System.getenv("SP_PASS");

    private static final String CLIENT_ID = System.getenv("SP_USER");

    // TODO: Create class that represents dictionary of artist albums to create the field
    // containing this information.
    public MyArtist(){
        // TODO: Implement constructor
        name = null;
        id = null;
        int y = 0;


    }






}
