package pl.ryszardszwajlik.twitter.transferObjects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MessageDTO
{
    private Long messageId;
    @NotNull
    @Size(min = 1, max = 140)
    private String content;
    @JsonSerialize(using = DateTimestampSerializer.class)
    private LocalDateTime createTime;

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
}
