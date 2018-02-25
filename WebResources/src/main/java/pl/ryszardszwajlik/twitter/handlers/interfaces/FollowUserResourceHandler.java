package pl.ryszardszwajlik.twitter.handlers.interfaces;

public interface FollowUserResourceHandler
{
    void followUser(Long userId, Long userToFollowId);
}
