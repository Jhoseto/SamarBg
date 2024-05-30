package org.samarBg.repository;

import org.samarBg.models.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository  extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByConversationId(Long conversationId);
}
