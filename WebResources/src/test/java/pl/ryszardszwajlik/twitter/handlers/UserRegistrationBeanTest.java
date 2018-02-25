package pl.ryszardszwajlik.twitter.handlers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.UserRepository;
import pl.ryszardszwajlik.twitter.handlers.interfaces.UserRegistration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRegistrationBean.class)
public class UserRegistrationBeanTest
{
    @Autowired
    private UserRegistration userRegistration;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldRegisterUser() throws Exception
    {
        // Given
        long userId = 1L;

        // When
        UserDAO registeredUser = userRegistration.registerUser(userId);

        // Then
        verify(userRepository).save(argThat((ArgumentMatcher<UserDAO>) userDAO ->
                userDAO.getUserId() == userId
        ));
        assertThat(registeredUser.getUserId(), is(equalTo(userId)));
    }
}