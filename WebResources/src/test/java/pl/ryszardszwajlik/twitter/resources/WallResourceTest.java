package pl.ryszardszwajlik.twitter.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.ryszardszwajlik.twitter.handlers.interfaces.WallResourceHandler;
import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WallResource.class)
@WebAppConfiguration
public class WallResourceTest
{
    @Autowired
    private WallResource wallResource;

    @MockBean
    private WallResourceHandler wallResourceHandler;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(wallResource).build();
    }

    @Test
    public void shouldHandleGetMethod() throws Exception
    {
        // Given
        long userId = 1L;
        Integer pageNumber = null;
        Integer pageSize = null;

        // When
        ResultActions resultActions = performGetWallAction(userId, pageNumber, pageSize);

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void shouldReturnWallInformation() throws Exception
    {
        // Given
        long userId = 1L;
        Integer pageNumber = null;
        Integer pageSize = null;

        PostsDTO postsDtoToReturn = new PostsDTO();
        postsDtoToReturn.setPosts(Collections.emptyList());
        when(wallResourceHandler.getUserWall(any(), any(), any())).thenReturn(postsDtoToReturn);

        // When
        ResultActions resultActions = performGetWallAction(userId, pageNumber, pageSize);

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(postsDtoToReturn)));
    }

    @Test
    public void shouldCallHandlerWithPaginationInformation() throws Exception
    {
        // Given
        long userId = 1L;
        Integer pageNumber = 2;
        Integer pageSize = 3;

        PostsDTO postsDtoToReturn = new PostsDTO();
        postsDtoToReturn.setPosts(Collections.emptyList());
        when(wallResourceHandler.getUserWall(any(), any(), any())).thenReturn(postsDtoToReturn);

        // When
        performGetWallAction(userId, pageNumber, pageSize);

        // Then
        verify(wallResourceHandler).getUserWall(eq(userId), eq(pageNumber), eq(pageSize));
    }

    private ResultActions performGetWallAction(Long userId, Integer pageNumber, Integer pageSize) throws Exception
    {
        MockHttpServletRequestBuilder requestBuilder = get("/wall")
                .param("userId", userId.toString());
        if (pageNumber != null)
        {
            requestBuilder.param("pageNumber", pageNumber.toString());
        }
        if (pageSize != null)
        {
            requestBuilder.param("pageSize", pageSize.toString());
        }
        return mockMvc.perform(requestBuilder);
    }
}