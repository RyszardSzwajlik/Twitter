package pl.ryszardSzwajlik.twitter.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostResource.class)
@WebAppConfiguration
public class PostResourceTest
{
    @Autowired
    private PostResource postResource;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(postResource).build();
    }

    @Test
    public void shouldHandlePutMethodForAddingNewMessage() throws Exception
    {
        mockMvc.perform(put("/post")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(resourcesContent("NewPostSimpleRequest.json")))
                .andExpect(status().isCreated());
    }

    private byte[] resourcesContent(String fileName) throws IOException, URISyntaxException
    {
        return Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
    }

}