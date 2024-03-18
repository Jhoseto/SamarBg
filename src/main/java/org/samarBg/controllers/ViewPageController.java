package org.samarBg.controllers;

import org.apache.tomcat.util.http.parser.Cookie;
import org.samarBg.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewPageController {


    @GetMapping("/index")
    public String showRegister(){
        return "index";
    }


    @GetMapping("/user-settings")
    public String showUserSettings(CurrentUser currentUser){
            return "user-settings";


    }


    @GetMapping("/userdetail")
    public String showUserDetail(CurrentUser currentUser){
            return "userdetail";
    }
}