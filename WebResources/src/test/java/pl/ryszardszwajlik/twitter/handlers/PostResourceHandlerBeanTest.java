package pl.ryszardszwajlik.twitter.handlers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.handlers.interfaces.PostResourceHandler;
import pl.ryszardszwajlik.twitter.handlers.interfaces.UserRegistration;
import pl.ryszardszwajlik.twitter.repository.MessageRepository;
import pl.ryszardszwajlik.twitter.repository.UserRepository;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostResourceHandlerBean.class)
public class PostResourceHandlerBeanTest
{
    @Autowired
    private PostResourceHandler postResourceHandler;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private UserRegistration userRegistration;

    @Test
    public void shouldCreateNewPostForExistingUser() throws Exception
    {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(1L);
        String message = RandomStringUtils.random(20);
        postDTO.setMessage(message);

        UserDAO existingUser = new UserDAO();
        existingUser.setUserId(1L);
        when(userRepository.findOne(Mockito.anyLong())).thenReturn(existingUser);

        // When
        postResourceHandler.createNewPost(postDTO);

        // Then
        verify(messageRepository).save(argThat((ArgumentMatcher<MessageDAO>) messageDAO -> {
            boolean hasValidMessage = messageDAO.getContent().equals(message);
            boolean hasValidUser = messageDAO.getCreatedBy() == existingUser;
            boolean hasCreateDateSet = messageDAO.getCreateTime() != null;
            return hasValidMessage && hasValidUser && hasCreateDateSet;
        }));
    }

    @Test
    public void shouldCreateNewPostForNewUserWhenUserDoesNotExist() throws Exception
    {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(1L);
        String message = RandomStringUtils.random(20);
        postDTO.setMessage(message);

        when(userRepository.findOne(Mockito.anyLong())).thenReturn(null);

        final UserDAO newUser = new UserDAO();
        newUser.setUserId(1L);
        when(userRegistration.registerUser(anyLong())).thenReturn(newUser);

        // When
        postResourceHandler.createNewPost(postDTO);

        // Then
        verify(messageRepository).save(argThat((ArgumentMatcher<MessageDAO>) messageDAO -> {
            boolean hasValidMessage = messageDAO.getContent().equals(message);
            boolean hasValidUser = messageDAO.getCreatedBy() == newUser;
            System.out.println(newUser.getUserId());
            boolean hasCreateDateSet = messageDAO.getCreateTime() != null;
            return hasValidMessage && hasValidUser && hasCreateDateSet;
        }));
    }

}