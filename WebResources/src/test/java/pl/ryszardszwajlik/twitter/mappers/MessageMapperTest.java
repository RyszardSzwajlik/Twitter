package pl.ryszardszwajlik.twitter.mappers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.builders.MessageDaoBuilder;
import pl.ryszardszwajlik.twitter.builders.UserDaoBuilder;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MessageMapperTest
{
    @Test
    public void shouldMapToPostDto() throws Exception
    {
        // Given
        String messageContent = RandomStringUtils.random(10);
        LocalDateTime createTime = LocalDateTime.now();
        long userId = 21L;
        MessageDAO messageDAO = new MessageDaoBuilder()
                .withContent(messageContent)
                .withUserDAO(new UserDaoBuilder()
                        .withUserId(userId)
                        .build())
                .withCreateTime(createTime)
                .build();

        // When
        PostDTO messageDTO = new MessageMapper().map(messageDAO);

        // Then
        assertThat(messageDTO.getUserId(), is(equalTo(userId)));
        assertThat(messageDTO.getMessage().getCreateTime(), is(equalTo(createTime)));
        assertThat(messageDTO.getMessage().getContent(), is(equalTo(messageContent)));
    }
}