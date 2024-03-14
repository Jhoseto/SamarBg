package org.samarBg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewPageController {
    @GetMapping("/index")
    public String showRegister(){
        return "index";
    }


    @GetMapping("/user-settings")
    public String showUserSettings(){
        return "user-settings";
    }
    @GetMapping("/userdetail")
    public String showUserDetail(){
        return "userdetail";
    }
}