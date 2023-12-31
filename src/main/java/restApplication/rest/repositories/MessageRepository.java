package restApplication.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restApplication.rest.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
