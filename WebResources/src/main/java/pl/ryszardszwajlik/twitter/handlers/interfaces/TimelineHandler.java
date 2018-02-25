package pl.ryszardszwajlik.twitter.handlers.interfaces;

import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

public interface TimelineHandler
{
    PostsDTO getTimeline(Long userId, Integer pageNumber, Integer pageSize);
}
