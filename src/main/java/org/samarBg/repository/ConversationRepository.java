package org.samarBg.repository;

import org.samarBg.models.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {

    ConversationEntity findByOfferId(Long conversationId);

}
