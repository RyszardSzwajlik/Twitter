package pl.ryszardszwajlik.twitter.handlers.interfaces;

import pl.ryszardszwajlik.twitter.UserDAO;

public interface UserRegistration
{
    UserDAO registerUser(Long userId);
}
