package pl.ryszardszwajlik.twitter.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.builders.UserDaoBuilder;
import pl.ryszardszwajlik.twitter.handlers.interfaces.UserRegistration;
import pl.ryszardszwajlik.twitter.repository.UserRepository;

@Component
public class UserRegistrationBean implements UserRegistration
{
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationBean.class);

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
        logger.info("New user registered: {}", userId);
        return user;
    }
}
