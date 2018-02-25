package pl.ryszardszwajlik.twitter.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.handlers.interfaces.FollowUserResourceHandler;
import pl.ryszardszwajlik.twitter.repository.UserRepository;

import javax.transaction.Transactional;

@Component
public class FollowUserResourceHandlerBean implements FollowUserResourceHandler
{
    private final static Logger logger = LoggerFactory.getLogger(FollowUserResourceHandlerBean.class);
    private final UserRepository userRepository;

    @Autowired
    public FollowUserResourceHandlerBean(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void followUser(Long userId, Long userToFollowId)
    {
        UserDAO user = userRepository.findOne(userId);
        UserDAO userToFollow = userRepository.findOne(userToFollowId);

        user.getFollowsUsers().add(userToFollow);
        userToFollow.getFollowedByUsers().add(user);

        logger.info("User {} follows {}", userId, userToFollowId);
    }
}
