package com.commerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jinwoo on 2017. 6. 21..
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "test";
    }
}
