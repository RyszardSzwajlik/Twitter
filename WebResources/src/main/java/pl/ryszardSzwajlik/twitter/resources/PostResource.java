package pl.ryszardSzwajlik.twitter.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.ryszardSzwajlik.twitter.transferObjects.PostDTO;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/post")
public class PostResource
{
    @RequestMapping(method = PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewMessage(@RequestBody PostDTO postDTO)
    {
    }
}
