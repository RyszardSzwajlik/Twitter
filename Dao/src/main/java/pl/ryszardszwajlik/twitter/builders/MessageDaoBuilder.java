package pl.ryszardszwajlik.twitter.builders;

import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.UserDAO;

import java.time.LocalDateTime;

public class MessageDaoBuilder
{
    private String content;
    private LocalDateTime createTime;
    private UserDAO userDAO;

    public MessageDAO build()
    {
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.setContent(content);
        messageDAO.setCreateTime(createTime);
        messageDAO.setCreatedBy(userDAO);
        return messageDAO;
    }

    public MessageDaoBuilder withContent(String content)
    {
        this.content = content;
        return this;
    }

    public MessageDaoBuilder withCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
        return this;
    }

    public MessageDaoBuilder withUserDAO(UserDAO userDAO)
    {
        this.userDAO = userDAO;
        return this;
    }
}
