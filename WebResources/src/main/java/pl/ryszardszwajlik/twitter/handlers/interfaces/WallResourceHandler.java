package pl.ryszardszwajlik.twitter.handlers.interfaces;

import pl.ryszardszwajlik.twitter.transferObjects.WallDTO;

public interface WallResourceHandler
{
    WallDTO getUserWall(Long userId, Integer pageNumber, Integer pageSize);
}
