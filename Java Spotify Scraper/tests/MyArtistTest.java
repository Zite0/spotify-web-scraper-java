import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import MyArtist.MyArtist;

import SpecialExceptions.NoResultException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.SpotifyApi;

public class MyArtistTest {

    /** SpotifyApi object to be used for testing.*/
    static SpotifyApi api;

    @Test
    void albumsTest(){
        assertThrows(NoResultException.class, () -> {
            throw new NoResultException("Message");
        });
    }

    @Test
    void artistTest(){

        // Instantiating a regular artist.
        MyArtist arcticMonkeys = new MyArtist("Arctic Monkeys");

        assertEquals("Arctic Monkeys",arcticMonkeys.getName());
        assertEquals("7Ln80lUS6He07XvHI8qqHH",arcticMonkeys.getId());


        // Meaningless name to instantiate MyArtist object. Should throw exception.
        assertThrows(NoResultException.class, () -> new MyArtist("mlaasidnaasdsd"));



    }
}
