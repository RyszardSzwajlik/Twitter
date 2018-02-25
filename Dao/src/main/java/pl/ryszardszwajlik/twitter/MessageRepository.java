package pl.ryszardszwajlik.twitter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageDAO, Long>
{
}
