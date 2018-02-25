package pl.ryszardSzwajlik.twitter.handlers.interfaces;

import pl.ryszardSzwajlik.twitter.transferObjects.PostDTO;

public interface PostResourceHandler
{
    void createNewPost(PostDTO postDTO);
}
