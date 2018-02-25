package pl.ryszardszwajlik.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ryszardszwajlik.twitter.UserDAO;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long>
{
}
