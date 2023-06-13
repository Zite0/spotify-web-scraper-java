import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import MyArtist.MyArtist;

import SpecialExceptions.NoResultException;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @Test
    void parseDateTest(){

        // Testing non-parsable string.
        assertThrows(NumberFormatException.class, () -> MyArtist.parseDate("kjansdjaks"));

        // Testing regular full date
        assertEquals(new GregorianCalendar(2001, Calendar.DECEMBER,31),
                MyArtist.parseDate("2001-12-31"));

        assertEquals(new GregorianCalendar(2004,Calendar.MARCH,18),
                MyArtist.parseDate("2004-03-18"));

        // No day, no month
        assertEquals(new GregorianCalendar(12,Calendar.JANUARY,1),MyArtist.parseDate("12"));

        // No day
        assertEquals(new GregorianCalendar(453,Calendar.SEPTEMBER,1),MyArtist.parseDate("453-9"));
    }
}
