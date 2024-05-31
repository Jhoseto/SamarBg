package org.samarBg.service;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.views.ConversationViewModel;
import java.util.List;

public interface ConversationService {

    List<ConversationEntity> getAllConversationsForUser(UserEntity user);

    List<ConversationViewModel> getAllNotificationForConversations(UserEntity user);

}
