package pl.ryszardszwajlik.twitter.handlers.interfaces;

import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

public interface WallResourceHandler
{
    PostsDTO getUserWall(Long userId, Integer pageNumber, Integer pageSize);
}
