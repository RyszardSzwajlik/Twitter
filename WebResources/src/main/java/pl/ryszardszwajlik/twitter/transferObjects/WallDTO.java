package pl.ryszardszwajlik.twitter.transferObjects;

import java.util.List;

public class WallDTO
{
    private List<MessageDTO> messages;

    public List<MessageDTO> getMessages()
    {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages)
    {
        this.messages = messages;
    }
}
