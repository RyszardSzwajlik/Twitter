package pl.ryszardszwajlik.twitter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "User")
public class UserDAO
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @OneToMany(mappedBy = "createdBy")
    private List<MessageDAO> messages;

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public List<MessageDAO> getMessages()
    {
        return messages;
    }

    public void setMessages(List<MessageDAO> messages)
    {
        this.messages = messages;
    }
}
