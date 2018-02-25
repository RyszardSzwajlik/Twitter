package pl.ryszardSzwajlik.twitter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.ryszardSzwajlik.twitter.handlers.interfaces.PostResourceHandler;
import pl.ryszardSzwajlik.twitter.transferObjects.PostDTO;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/post")
public class PostResource
{
    private PostResourceHandler postResourceHandler;

    @Autowired
    public PostResource(PostResourceHandler postResourceHandler)
    {
        this.postResourceHandler = postResourceHandler;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewMessage(@Validated @RequestBody PostDTO postDTO)
    {
        postResourceHandler.createNewPost(postDTO);
    }
}
