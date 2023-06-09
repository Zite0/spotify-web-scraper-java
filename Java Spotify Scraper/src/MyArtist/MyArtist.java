package MyArtist;

import java.io.IOException;
import java.util.*;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

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

    /** A final String containing the artist's name.*/
    private final String name;


    /** A String that represents this artist's unique id used by Spotify. */
    private final String id;

    /**
     * Albums object that contains an artist's albums and the year they were released, as well
     * the songs on each album.
     */
    // TODO: employ APIs capabilities to fetch multiple albums at the same time.
    private MyAlbum[] albums;

    /** Array of strings containing the id associated to each album in field 'albums'.
     * Should not be used for any other purpose. */
    private final String[] albumIds = {};

    /** SpotifyApi object provided by the same library, used for getting data from Spotify.*/
    private SpotifyApi spotifyApi;

    // FIXME: environment variable access.
    private static final String CLIENT_SECRET = System.getenv("SP_PASS");

    private static final String CLIENT_ID = System.getenv("SP_USER");

    public MyArtist(String clientName){
        // TODO: Implement constructor
        name = null;
        id = null;
        int y = 0;
    }

    /** Returns this artist's name.*/
    public String getName(){
        return name;
    }

    /** Returns this artist's Spotify id.*/
    public String getId(){
        return id;
    }


    /**
     * Initializes SpotifyApi object for use in the other methods of this class; to be used
     * as a helper when instantiating class.
     */
    private void setAPI() {

        // Instantiates object.
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .build();


        // Requests client credentials from the API, which are used for requests that don't
        // require accessing user information.
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();

        try {
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /** Helper method for MyArtist's constructor. Sets the albums field of this artist object. */
    private void setAlbums(){
        throw new UnsupportedOperationException();
    }








}
