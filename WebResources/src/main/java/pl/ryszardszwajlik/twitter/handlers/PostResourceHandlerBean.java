package pl.ryszardszwajlik.twitter.handlers;

import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.handlers.interfaces.PostResourceHandler;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;

@Component
public class PostResourceHandlerBean implements PostResourceHandler
{

    @Override
    public void createNewPost(PostDTO postDTO)
    {

    }
}
