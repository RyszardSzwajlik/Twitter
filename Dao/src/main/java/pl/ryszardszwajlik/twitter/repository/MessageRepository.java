package pl.ryszardszwajlik.twitter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.ryszardszwajlik.twitter.MessageDAO;

public interface MessageRepository extends PagingAndSortingRepository<MessageDAO, Long>
{
    Page<MessageDAO> findByCreatedByUserId(Long userId, Pageable pageRequest);
}
