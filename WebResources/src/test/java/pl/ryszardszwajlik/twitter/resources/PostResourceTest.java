package pl.ryszardszwajlik.twitter.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.ryszardszwajlik.twitter.handlers.interfaces.PostResourceHandler;
import pl.ryszardszwajlik.twitter.transferObjects.MessageDTO;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostResource.class)
@WebAppConfiguration
public class PostResourceTest
{
    @Autowired
    private PostResource postResource;

    @MockBean
    private PostResourceHandler postResourceHandler;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(postResource).build();
    }

    @Test
    public void shouldHandlePostMethodForAddingNewMessage() throws Exception
    {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(1L);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(RandomStringUtils.random(140));
        postDTO.setMessage(messageDTO);

        // When
        ResultActions resultActions = performCreateNewPostAction(postDTO);

        // Then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void postMethodShouldNotAcceptNullUserId() throws Exception
    {
        // Given
        PostDTO postDTO = new PostDTO();
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(RandomStringUtils.random(10));
        postDTO.setMessage(messageDTO);

        // When
        ResultActions resultActions = performCreateNewPostAction(postDTO);

        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void postMethodShouldNotAcceptNullOrEmptyMessage() throws Exception
    {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(1L);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent("");
        postDTO.setMessage(messageDTO);

        // When
        ResultActions resultActions = performCreateNewPostAction(postDTO);

        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void postMethodShouldNotAcceptMessagesLongerThen140Characters() throws Exception
    {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(1L);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(RandomStringUtils.random(141));
        postDTO.setMessage(messageDTO);

        // When
        ResultActions resultActions = performCreateNewPostAction(postDTO);

        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    private ResultActions performCreateNewPostAction(PostDTO postDTO) throws Exception
    {
        return mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(postDTO)));
    }

}