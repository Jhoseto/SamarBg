package org.samarBg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchingController {


    @GetMapping("/searching")
    public String showSearchingPage(){
        return "searching";
    }

}
