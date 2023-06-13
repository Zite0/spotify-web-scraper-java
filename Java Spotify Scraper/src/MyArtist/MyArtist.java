package MyArtist;

import SpecialExceptions.NoResultException;
import com.neovisionaries.i18n.CountryCode;
import java.io.IOException;
import java.util.*;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

/**
 * MyArtist class, which represents an individual artist with data scraped using the Spotify Web API
 * project. The class was originally implemented by michelle-mrz on the Spotify Web Scraper project.
 * The class is named like so to avoid confusion with the one provided by the Spotify Web API
 * utility. The decision to make this extra class was for simplicity, as much of what is provided by
 * the aforementioned library is useful yet unnecessary for our purposes.
 */
public class MyArtist {

    //TODO: find a way to minimize number of requests made to the API/ figure out a way
    // to use multiple client ids/secrets to be able to make more requests.

    /** A final String containing the artist's name. */
    private String name;

    /** A String that represents this artist's unique id used by Spotify. */
    private String id;

    /**
     * Albums object that contains an artist's albums and the year they were released, as well the
     * songs on each album. Albums are limited to those released in the US.
     */
    private MyAlbum[] albums;

    /**
     * SpotifyApi object provided by the same library, used for getting data from Spotify.
     */
    private SpotifyApi spotifyApi;

    /**
     * Client secret credential. Must be set as environment variable and is required to log in to
     * the Spotify API.
     */
    private static final String CLIENT_SECRET = System.getenv("SP_PASS");

    /**
     * Client id credential. Must be set as environment variable and is required to log in to the
     * Spotify API.
     */
    private static final String CLIENT_ID = System.getenv("SP_USER");

    /** MyArtist constructor. Takes string 'clientArtistName', which should be
     *  used in non-concurrent instantiation, as it constructs a SpotifyApi
     * object for every method call. Refer to the other constructor for a concurrent friendly
     * implementation. Requires that the environment variables 'SP_USER' and 'SP_PASS' be set to
     * valid a valid client ID and secret. Use only for immediate testing and rapid results.
     * @throws NoResultException if the user entered a meaningless string, such as 'asdasd'.
     * @param clientArtistName The name of an artist to be searched on the Spotify API
     *  */
    public MyArtist(String clientArtistName) {
        setAPI();
        searchArtist(clientArtistName);
        setAlbums();
    }

    /** A concurrent implementation of MyArtist construction because it allows for instantiation of
     * multiple artists with the same SpotifyApi object. Fetching multiple artist's id and their
     * information is to be done using a class that requests multiple artists at the same time.
     * @param id the unique id of this artist.
     * @param api a SpotifyApi that has already been instantiated and is ready to be used for
     * requests. See the spotify-web-api-java library to see how to do this.
     */
    public MyArtist(String id, SpotifyApi api, String name){
        spotifyApi = api;
        this.id = id;
        this.name = name;
        setAlbums();
    }

    /** Takes a string that must be formatted as a date using dashes in the format
     * YEAR-MONTH-DAY, where each substring is a numeric value. For instance, 1999-11-19 is a
     * valid date. Note that, for our purposes, a given String may not have full precision, so it
     * may be missing the month and day, which is okay. If the string contains no month, then
     * January is used by default. If it contains no day, the first of the month is used.
     * ----
     * Requires that the second substring be a substring be a number between 1 and 12,
     * non-inclusive, and that the third be a number between 1 and 31, inclusive.
     * Years must be numbers greater than 0 and cannot be omitted.
     * Precision must be reduced in the order of day and then month.
     * @param date A date of the form YEAR-MONTH-DAY.
     * @throws NumberFormatException if the substrings are not numeric.
     */
    public static Calendar parseDate (String date){
        // Spotify API uses dashes to separate date, so we use String.split()
        String[] section = date.split("-");

        try{
            switch (section.length) {
                case 1 -> {
                    return new GregorianCalendar(Integer.parseInt(section[0])
                            , Calendar.JANUARY, 1);
                }
                case 2 -> {
                    return new GregorianCalendar(Integer.parseInt(section[0])
                            , Integer.parseInt(section[1]) - 1, 1);
                }
                case 3 -> {
                    return new GregorianCalendar(Integer.parseInt(section[0])
                            , Integer.parseInt(section[1]) - 1, Integer.parseInt(section[2]));
                }
            }
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("String could not be converted to an integer.");
        }
        // Should be unreachable if preconditions are met.
        return null;
    }

    /**
     * Returns this artist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this artist's Spotify id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the number of albums by this artist.
     */
    public int getNumberOfAlbums() {
        return albums.length;
    }

    /**
     * Method that requests an artist from the Spotify API given their name 'name'. Throws
     * NoResultException if the request produced no result, possibly due to the user entering a
     * meaningless name, such as 'asdasd'.
     */
    private void searchArtist(String name) {
        // Defines the parameters of request. The type of the request is
        // predetermined to be ARTIST, as specified in the official Spotify
        // API.
        SearchItemRequest searchItemRequest = spotifyApi.searchItem(name,
                        ModelObjectType.ARTIST.getType())
                .market(CountryCode.US)
                // Result limited only to the very first artist.
                .limit(1)
                .build();

        try {
            //Executes request.
            SearchResult result = searchItemRequest.execute();

            Artist[] spotifyArtistList = result.getArtists().getItems();

            if (spotifyArtistList.length == 0) {
                // Throws an exception iff the result contained no items.
                throw new NoResultException("Request produced no results.");
            }

            Artist artist = spotifyArtistList[0];

            // Sets this object's id and name.
            this.id = artist.getId();
            this.name = artist.getName();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Initializes SpotifyApi object for use in the other methods of this class; to be used as a
     * helper when instantiating class.
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

        // Setting client credentials.
        try {
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException("Unable to get Client Credentials.");
        }

    }

    /**
     * Helper method for MyArtist's constructor. Sets the albums field of this artist object.
     */
    private void setAlbums() {
        System.out.println("Hello, World!");

        Arrays.sort(albums);
        // TODO: set size and use get artist albums.
    }


}
