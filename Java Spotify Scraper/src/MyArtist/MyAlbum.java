package MyArtist;
import com.neovisionaries.i18n.CountryCode;
import java.util.Calendar;
import java.util.List;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;

// TODO: Implement class

/**
 * Protected class that wraps around several of the provided Album classes in the Spotify Web API,
 * which contain useful yet needless features that would make developing the functionality
 * of the entire scraper too complicated.
 */
class MyAlbum{

    /** String containing the id 'id' of this album. */
    private final String id;

    /** Release date of this album, stored as Date object. */
    private final Calendar releaseDate = null;

    /** The number of songs on this album, a positive integer less than 30.*/
    private int size;

    /** String array containing the tracks of this album, ordered in the same way they appear
     * in Spotify. */
    private final String[] tracks = null;

    /** This album's title.*/
    private final String title = "";

    /** The type of this album, according to the SpotifyAPI doc. Other types are specified in
     * one of the spotify-web-api-java classes.*/
    private String albumType;

    /**
     * SpotifyApi object provided by the same library, used for getting data from Spotify.
     * Not instantiated in this object but passed as an argument in the constructor.
     */
    private final SpotifyApi spotifyApi;

    /** Constructor of MyAlbum method.
     * @param id Unique id of this album, as found in the official Spotify API.
     * @param api Initialized object, used to make requests to the Spotify API.
     * @param albumType The unique id of this album, provided by the Spotify API.
     *
     */
    protected MyAlbum(String id, SpotifyApi api,String albumType){
        this.id = id;
        spotifyApi = api;
        this.albumType = albumType;
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

    /**
     * Requests this album's tracks and sets the 'tracks' field.
     */
    private void searchAlbum(){
        // Instantiates a GetArtistsAlbumsRequest to get
        GetAlbumsTracksRequest tracksRequest = spotifyApi.getAlbumsTracks(id)
                .market(CountryCode.US)
                .limit();
    }
}

