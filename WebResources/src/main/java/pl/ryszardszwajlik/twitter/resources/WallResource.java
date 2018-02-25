package pl.ryszardszwajlik.twitter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ryszardszwajlik.twitter.handlers.interfaces.WallResourceHandler;
import pl.ryszardszwajlik.twitter.transferObjects.WallDTO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/wall")
public class WallResource
{
    private final WallResourceHandler wallResourceHandler;

    @Autowired
    public WallResource(WallResourceHandler wallResourceHandler)
    {
        this.wallResourceHandler = wallResourceHandler;
    }

    @RequestMapping(method = GET)
    public WallDTO getUserWall(@RequestParam("userId") Long userId,
                               @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize)
    {
        return wallResourceHandler.getUserWall(userId, pageNumber, pageSize);
    }
}
