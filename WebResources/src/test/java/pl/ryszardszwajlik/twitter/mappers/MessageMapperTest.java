package pl.ryszardszwajlik.twitter.mappers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.builders.MessageDaoBuilder;
import pl.ryszardszwajlik.twitter.transferObjects.MessageDTO;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MessageMapperTest
{
    @Test
    public void shouldMapToMessageDto() throws Exception
    {
        // Given
        String messageContent = RandomStringUtils.random(10);
        LocalDateTime createTime = LocalDateTime.now();
        MessageDAO messageDAO = new MessageDaoBuilder()
                .withContent(messageContent)
                .withCreateTime(createTime)
                .build();

        // When
        MessageDTO messageDTO = new MessageMapper().map(messageDAO);

        // Then
        assertThat(messageDTO.getContent(), is(equalTo(messageContent)));
        assertThat(messageDTO.getCreateTime(), is(equalTo(createTime)));
    }
}