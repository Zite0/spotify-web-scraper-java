package MyArtist;

import java.util.Calendar;
import se.michaelthelin.spotify.SpotifyApi;

/** Subclass of MyAlbum whose album type is 'single' as described in the
 * Spotify API documentation.*/
public class MySingle extends MyAlbum{

    /** Constructor of MySingle object. /** Constructor of MyAlbum method.
     * @param id Unique id of this single, as found in the official Spotify API.
     * @param title The title of this single.
     * @param api Correctly initialized object, used to make requests to the Spotify API.
     * @param albumType The unique id of this album, provided by the Spotify API.
     * @param size The number of tracks on this album.
     * @param date This album's release date.
     */
    protected MySingle(String id, String title, SpotifyApi api, String albumType, int size,
            Calendar date){
        super(id,title,api,albumType,size,date);
    }
}
