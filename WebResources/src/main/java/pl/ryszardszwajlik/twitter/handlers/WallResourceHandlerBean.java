package pl.ryszardszwajlik.twitter.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.handlers.interfaces.WallResourceHandler;
import pl.ryszardszwajlik.twitter.mappers.MessageMapper;
import pl.ryszardszwajlik.twitter.repository.MessageRepository;
import pl.ryszardszwajlik.twitter.transferObjects.PostDTO;
import pl.ryszardszwajlik.twitter.transferObjects.PostsDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class WallResourceHandlerBean implements WallResourceHandler
{
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public WallResourceHandlerBean(MessageRepository messageRepository, MessageMapper messageMapper)
    {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public PostsDTO getUserWall(Long userId, Integer pageNumber, Integer pageSize)
    {
        Page<MessageDAO> pagedMessages = getMessageDAOs(userId, pageNumber, pageSize);
        List<PostDTO> messagesDTO = mapToPosts(pagedMessages);

        PostsDTO postsDTO = new PostsDTO();
        postsDTO.setPosts(messagesDTO);
        return postsDTO;
    }

    private Page<MessageDAO> getMessageDAOs(Long userId, Integer pageNumber, Integer pageSize)
    {
        return messageRepository.findByCreatedByUserId(userId, new PageRequest(pageNumber, pageSize,
                new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"))));
    }

    private List<PostDTO> mapToPosts(Page<MessageDAO> pagedMessages)
    {
        return StreamSupport.stream(pagedMessages.spliterator(), false)
                .map(messageMapper::map)
                .collect(Collectors.toList());
    }
}
