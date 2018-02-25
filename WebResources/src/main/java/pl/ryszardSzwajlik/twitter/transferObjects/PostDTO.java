package pl.ryszardSzwajlik.twitter.transferObjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostDTO
{
    @NotNull
    private Long userId;

    @NotNull
    @Size(min = 1, max = 140)
    private String message;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
