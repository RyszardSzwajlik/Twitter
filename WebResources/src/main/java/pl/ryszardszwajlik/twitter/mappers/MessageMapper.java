package pl.ryszardszwajlik.twitter.mappers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import pl.ryszardszwajlik.twitter.MessageDAO;
import pl.ryszardszwajlik.twitter.transferObjects.MessageDTO;

@Component
public class MessageMapper
{
    private final MapperFacade messagesMapper;

    public MessageMapper()
    {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(MessageDAO.class, MessageDTO.class);
        messagesMapper = mapperFactory.getMapperFacade();
    }

    public MessageDTO map(MessageDAO messageDAO)
    {
        return messagesMapper.map(messageDAO, MessageDTO.class);
    }
}
