package pl.ryszardszwajlik.twitter.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(Long message)
    {
        super("User id not found: " + message);
    }
}
