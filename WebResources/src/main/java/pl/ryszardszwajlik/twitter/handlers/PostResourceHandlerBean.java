package pl.ryszardszwajlik.twitter.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.builders.MessageDaoBuilder;
import pl.ryszardszwajlik.twitter.handlers.interfaces.PostResourceHandler;
import pl.ryszardszwajlik.twitter.handlers.interfaces.UserRegistration;
import pl.ryszardszwajlik.twitter.repository.MessageRepository;
import pl.ryszardszwajlik.twitter.repository.UserRepository;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;

import java.time.LocalDateTime;

@Component
public class PostResourceHandlerBean implements PostResourceHandler
{
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final UserRegistration userRegistration;

    @Autowired
    public PostResourceHandlerBean(UserRepository userRepository, MessageRepository messageRepository, UserRegistration userRegistration)
    {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.userRegistration = userRegistration;
    }

    @Override
    public void createNewPost(PostDTO postDTO)
    {
        UserDAO user = getExistingUserOrCreateNewOne(postDTO.getUserId());
        MessageDAO messageDAO = buildMessage(postDTO, user);
        messageRepository.save(messageDAO);
    }

    private UserDAO getExistingUserOrCreateNewOne(Long userId)
    {
        UserDAO user = userRepository.findOne(userId);
        if (user == null)
        {
            user = userRegistration.registerUser(userId);
        }
        return user;
    }

    private MessageDAO buildMessage(PostDTO postDTO, UserDAO user)
    {
        MessageDAO messageDAO = new MessageDaoBuilder()
                .withContent(postDTO.getMessage())
                .withCreateTime(LocalDateTime.now())
                .build();
        messageDAO.setCreatedBy(user);
        return messageDAO;
    }
}
