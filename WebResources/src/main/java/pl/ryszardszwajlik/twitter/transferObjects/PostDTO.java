package pl.ryszardszwajlik.twitter.transferObjects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PostDTO
{
    @NotNull
    private Long userId;

    @Valid
    private MessageDTO message;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public MessageDTO getMessage()
    {
        return message;
    }

    public void setMessage(MessageDTO message)
    {
        this.message = message;
    }
}
