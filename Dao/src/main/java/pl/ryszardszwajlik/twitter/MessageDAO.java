package pl.ryszardszwajlik.twitter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class MessageDAO
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    private String content;

    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDAO createdBy;

    public Long getMessageId()
    {
        return messageId;
    }

    public void setMessageId(Long messageId)
    {
        this.messageId = messageId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    public UserDAO getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(UserDAO createdBy)
    {
        this.createdBy = createdBy;
    }
}
