package pl.ryszardszwajlik.twitter.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.exceptions.UserNotFoundException;
import pl.ryszardszwajlik.twitter.handlers.interfaces.TimelineHandler;
import pl.ryszardszwajlik.twitter.mappers.MessageMapper;
import pl.ryszardszwajlik.twitter.repository.MessageRepository;
import pl.ryszardszwajlik.twitter.repository.UserRepository;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;
import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

@Component
public class TimelineHandlerBean implements TimelineHandler
{
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public TimelineHandlerBean(MessageRepository messageRepository, UserRepository userRepository, MessageMapper messageMapper)
    {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public PostsDTO getTimeline(Long userId, Integer pageNumber, Integer pageSize)
    {
        UserDAO userDAO = userRepository.findOne(userId);

        Set<Long> userIdsThatUserFollows = ofNullable(userDAO)
                .map(user -> user.getFollowsUsers().stream())
                .orElseThrow(() -> new UserNotFoundException(userId))
                .map(UserDAO::getUserId)
                .collect(Collectors.toSet());
        Page<MessageDAO> pagedMessages = getMessageDAOs(userIdsThatUserFollows, pageNumber, pageSize);
        List<PostDTO> messagesDTO = mapToPosts(pagedMessages);

        PostsDTO postsDTO = new PostsDTO();
        postsDTO.setPosts(messagesDTO);
        return postsDTO;
    }

    private Page<MessageDAO> getMessageDAOs(Set<Long> followsUsers, Integer pageNumber, Integer pageSize)
    {
        return messageRepository.findByCreatedByUserIdIn(followsUsers, new PageRequest(pageNumber, pageSize,
                new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"))));
    }

    private List<PostDTO> mapToPosts(Page<MessageDAO> pagedMessages)
    {
        return StreamSupport.stream(pagedMessages.spliterator(), false)
                .map(messageMapper::map)
                .collect(Collectors.toList());
    }
}
