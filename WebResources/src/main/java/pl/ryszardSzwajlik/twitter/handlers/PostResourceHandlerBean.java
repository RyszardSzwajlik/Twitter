package pl.ryszardSzwajlik.twitter.handlers;

import org.springframework.stereotype.Component;
import pl.ryszardSzwajlik.twitter.handlers.interfaces.PostResourceHandler;
import pl.ryszardSzwajlik.twitter.transferObjects.PostDTO;

@Component
public class PostResourceHandlerBean implements PostResourceHandler
{

    @Override
    public void createNewPost(PostDTO postDTO)
    {

    }
}
