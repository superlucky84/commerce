package com.commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jinwoo on 2017. 6. 22..
 */
@Controller
public class UserController {

    @RequestMapping("/join")
    public String join(Model model) {

        return "test";
    }

    @RequestMapping("/login")
    public String login(Model model) {

        return "test";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {

        return "test";
    }
}
