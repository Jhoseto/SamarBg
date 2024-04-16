package org.samarBg.controllers;

import org.samarBg.service.UserService;
import org.samarBg.service.WebSocketService;
import org.samarBg.views.ChatMessageViewModel;
import org.samarBg.views.UserProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {

    private final WebSocketService webSocketService;
    private final UserService userService;

    @Autowired
    public ChatController(WebSocketService webSocketService,
                          UserService userService) {
        this.webSocketService = webSocketService;

        this.userService = userService;
    }

//    @GetMapping("/index/#")
//    public String chat(Model model) {
//        List<UserProfileViewModel> users = userService.getAllUsers();
//
//        if (!users.isEmpty()) {
//            UserProfileViewModel selectedUser = users.get(0);
//            List<ChatMessageViewModel> chatMessages = webSocketService.getChatMessages("selectedUserName");
//
//            model.addAttribute("users", users);
//            model.addAttribute("selectedUser", selectedUser);
//            model.addAttribute("chatMessages", chatMessages);
//        }
//        return "fragments/chatPanel";
//    }
}
