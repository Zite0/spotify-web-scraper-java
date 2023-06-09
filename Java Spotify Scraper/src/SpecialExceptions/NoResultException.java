package SpecialExceptions;

/**
 * Special exception to be thrown when the Spotify API library returns a query with no results,
 * usually as a result of the user entering a meaningless artist name
 * when instantiating a MyArtist object.
 */
public class NoResultException extends RuntimeException {

    /** Throws exception. */
    public NoResultException(String message){
        super(message);
    }

}
