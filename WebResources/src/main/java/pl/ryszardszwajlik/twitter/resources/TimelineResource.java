package pl.ryszardszwajlik.twitter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ryszardszwajlik.twitter.handlers.interfaces.TimelineHandler;
import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/timeline")
public class TimelineResource
{
    private final TimelineHandler timelineHandler;

    @Autowired
    public TimelineResource(TimelineHandler timelineHandler)
    {
        this.timelineHandler = timelineHandler;
    }

    @RequestMapping(method = GET)
    public PostsDTO getTimeline(@RequestParam("userId") Long userId,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize)
    {
        return timelineHandler.getTimeline(userId, pageNumber, pageSize);
    }
}
