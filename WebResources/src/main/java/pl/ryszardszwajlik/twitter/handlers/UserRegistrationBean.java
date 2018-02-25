package pl.ryszardszwajlik.twitter.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.UserRepository;
import pl.ryszardszwajlik.twitter.builders.UserDaoBuilder;
import pl.ryszardszwajlik.twitter.handlers.interfaces.UserRegistration;

@Component
public class UserRegistrationBean implements UserRegistration
{
    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationBean(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDAO registerUser(Long userId)
    {
        UserDAO user = new UserDaoBuilder()
                .withUserId(userId)
                .build();
        userRepository.save(user);
        return user;
    }
}
