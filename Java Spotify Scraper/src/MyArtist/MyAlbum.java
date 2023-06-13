package MyArtist;
import com.neovisionaries.i18n.CountryCode;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;

// TODO: Implement class

/**
 * Protected class that wraps around several of the provided Album classes in the Spotify Web API,
 * which contain useful yet needless features that would make developing the functionality
 * of the entire scraper too complicated.
 */
class MyAlbum implements Comparable<MyAlbum>{

    /** String containing the id 'id' of this album. */
    private final String id;

    /** Release date of this album, stored as Date object. */
    private final Calendar releaseDate;

    /** The number of songs on this album, a positive integer less than 40. */
    private final int size;

    /** String array containing the tracks of this album, ordered in the same way they appear
     * in Spotify. Invariant: the length of this array is equal to the size field. */
    private String[] tracks;

    /** This album's title.*/
    private final String title;

    /** The type of this album, according to the SpotifyAPI doc. Other types are specified in
     * one of the spotify-web-api-java classes. */
    private final String albumType;

    /**
     * SpotifyApi object provided by the same library, used for getting data from Spotify.
     * Not instantiated in this object but passed as an argument in the constructor.
     */
    private final SpotifyApi spotifyApi;

    /** Constructor of MyAlbum method.
     * @param id Unique id of this album, as found in the official Spotify API.
     * @param title The title of this album.
     * @param api Correctly initialized object, used to make requests to the Spotify API.
     * @param albumType The unique id of this album, provided by the Spotify API.
     * @param size The number of tracks on this album.
     * @param date This album's release date.
     */
    protected MyAlbum(String id,String title,SpotifyApi api,String albumType,int size
    ,Calendar date){
        this.id = id;
        this.title = title;
        spotifyApi = api;
        this.albumType = albumType;
        this.size = size;
        releaseDate = date;
    }

    /** Returns a String array with the tracks of this album. */
    public String[] getTracks(){
        return tracks;
    }

    /** Returns this album's title. */
    public String getTitle(){
        return title;
    }

    /** Returns this album's unique id. */
    public String getId(){
        return id;
    }

    /** Returns the number of tracks on this album. */
    public int getSize(){
        return size;
    }

    /** Returns this album's type: 'album', 'compilation', or 'single'. */
    public String getAlbumType(){
       return albumType;
    }

    /** Returns this album's release date as a string. */
    public Calendar getReleaseDate(){
        return releaseDate;
    }

    /** Compares two MyAlbum objects; this comparison is made based on this album's release date.
     * @return the value 0 if this album was released on the same date as 'other'; a value less
     * than 0 if this album was released before 'other'; otherwise, a value greater than 0. */
    @Override
    public int compareTo(MyAlbum other){
        return this.releaseDate.compareTo(other.releaseDate);
    }

    /** Requests this album's tracks and sets the 'tracks' field. */
    private void searchTracks() throws IOException, ParseException, SpotifyWebApiException {
        // Instantiates a GetArtistsAlbumsRequest to request album tracks.
        GetAlbumsTracksRequest tracksRequest = spotifyApi.getAlbumsTracks(id)
                .market(CountryCode.US)
                .limit(30)
                .build();

        Paging<TrackSimplified> trackSimplifiedPaging = tracksRequest.execute();

        // Instantiates array with tracks.
        tracks = new String[size];
        int i = 0;
        for (TrackSimplified track : trackSimplifiedPaging.getItems()){
            tracks[i] = track.getName();
            i++;
        }

    }

}

