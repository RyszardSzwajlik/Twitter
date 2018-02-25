package pl.ryszardszwajlik.twitter.builders;

import pl.ryszardszwajlik.twitter.UserDAO;

public class UserDaoBuilder
{
    private long userId;

    public UserDAO build()
    {
        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(userId);
        return userDAO;
    }

    public UserDaoBuilder withUserId(long userId)
    {
        this.userId = userId;
        return this;
    }
}
