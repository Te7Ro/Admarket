package kz.guccigang.admarket.repository.communication;

import kz.guccigang.admarket.entity.communication.Message;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends BaseRepository<Message> {
}
