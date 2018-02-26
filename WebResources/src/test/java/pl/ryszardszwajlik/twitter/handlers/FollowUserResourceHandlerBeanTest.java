package pl.ryszardszwajlik.twitter.handlers;

import org.junit.Test;
import org.mockito.Mockito;
import pl.ryszardszwajlik.twitter.exceptions.UserNotFoundException;
import pl.ryszardszwajlik.twitter.repository.UserRepository;

public class FollowUserResourceHandlerBeanTest
{
    @Test(expected = UserNotFoundException.class)
    public void shouldThrowValidExceptionWhenUserNotFound() throws Exception
    {
        // Given
        long notFoundUserId = 1L;
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findOne(Mockito.eq(notFoundUserId))).thenReturn(null);

        // When
        new FollowUserResourceHandlerBean(userRepository).followUser(notFoundUserId, 2L);

        // Then

    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowValidExceptionWhenUserToFollowNotFound() throws Exception
    {
        // Given
        long notFoundUserId = 2L;
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findOne(Mockito.eq(notFoundUserId))).thenReturn(null);

        // When
        new FollowUserResourceHandlerBean(userRepository).followUser(1L, notFoundUserId);

        // Then

    }
}