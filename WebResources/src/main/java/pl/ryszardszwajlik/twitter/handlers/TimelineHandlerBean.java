package pl.ryszardszwajlik.twitter.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.UserDAO;
import pl.ryszardszwajlik.twitter.handlers.interfaces.TimelineHandler;
import pl.ryszardszwajlik.twitter.mappers.MessageMapper;
import pl.ryszardszwajlik.twitter.repository.MessageRepository;
import pl.ryszardszwajlik.twitter.repository.UserRepository;
import pl.ryszardszwajlik.twitter.transferObjects.MessageDTO;
import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        UserDAO user = userRepository.findOne(userId);
        Set<Long> userIdsThatUserFollows = user.getFollowsUsers().stream()
                .map(UserDAO::getUserId)
                .collect(Collectors.toSet());
        Page<MessageDAO> pagedMessages = getMessageDAOs(userIdsThatUserFollows, pageNumber, pageSize);
        List<MessageDTO> messagesDTO = mapToDTO(pagedMessages);

        PostsDTO postsDTO = new PostsDTO();
        postsDTO.setMessages(messagesDTO);
        return postsDTO;
    }

    private Page<MessageDAO> getMessageDAOs(Set<Long> followsUsers, Integer pageNumber, Integer pageSize)
    {
        return messageRepository.findByCreatedByUserIdIn(followsUsers, new PageRequest(pageNumber, pageSize,
                new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"))));
    }

    private List<MessageDTO> mapToDTO(Page<MessageDAO> pagedMessages)
    {
        return StreamSupport.stream(pagedMessages.spliterator(), false)
                .map(messageMapper::map)
                .collect(Collectors.toList());
    }
}
