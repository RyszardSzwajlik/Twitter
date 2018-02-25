package pl.ryszardszwajlik.twitter.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.ryszardszwajlik.twitter.handlers.interfaces.FollowUserResourceHandler;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/followUser")
public class FollowUserResource
{
    private final FollowUserResourceHandler followUserResourceHandler;

    @Autowired
    public FollowUserResource(FollowUserResourceHandler followUserResourceHandler)
    {
        this.followUserResourceHandler = followUserResourceHandler;
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(NO_CONTENT)
    public void followUser(@RequestParam("userId") Long userId, @RequestParam("userToFollowId") Long userToFollowId)
    {
        followUserResourceHandler.followUser(userId, userToFollowId);
    }
}
