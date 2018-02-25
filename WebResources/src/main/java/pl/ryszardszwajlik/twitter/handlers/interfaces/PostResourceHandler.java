package pl.ryszardszwajlik.twitter.handlers.interfaces;

import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;

public interface PostResourceHandler
{
    void createNewPost(PostDTO postDTO);
}
