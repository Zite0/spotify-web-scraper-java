package MyArtist;
import com.neovisionaries.i18n.CountryCode;
import java.io.IOException;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.model_objects.specification.Artist;


public class Test {

    static void search() throws IOException, ParseException, SpotifyWebApiException {

        String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId("936004f3ef804d04b78af09dbbcd8357")
                .setClientSecret("56f682b2a40b4bb1b645cd2030271a49")
                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();



        final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

        // Set access token for further "spotifyApi" object usage
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());



        final SearchItemRequest searchItemRequest = spotifyApi.searchItem("banda el recodo",
                        ModelObjectType.ARTIST.getType())
          .market(CountryCode.US)
          .limit(1)
//          .offset(0)
//          .includeExternal("audio")
            .build();




        try {

            SearchResult result = searchItemRequest.execute();
            String id = result.getArtists().getItems()[0].getId();

            final GetArtistsAlbumsRequest albumRequest = spotifyApi.getArtistsAlbums(id)
                    .market(CountryCode.US)
                    .album_type(ModelObjectType.ALBUM.getType())
                    .build();


            Paging<AlbumSimplified> albums = albumRequest.execute();

            AlbumSimplified[] list = albums.getItems();

            AlbumSimplified thisAlbum = list[0];

            String thisAlbumId = thisAlbum.getId();
            System.out.println("Album was released in " + thisAlbum.getReleaseDate());

            GetAlbumsTracksRequest tracksRequest = spotifyApi.getAlbumsTracks(thisAlbumId)
                    .market(CountryCode.US)
                    .limit(20)
                    .build();


            Paging<TrackSimplified> MyPagingTracks = tracksRequest.execute();

            TrackSimplified[] listOfTracks = MyPagingTracks.getItems();

            for (TrackSimplified track : listOfTracks){
                System.out.println(track.getName());
            }






        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
            // hello hello
        }
    }

    public static void main(String[] args){
        try {
            search();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }
    }

}
