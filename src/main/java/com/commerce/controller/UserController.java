package com.commerce.controller;

import com.commerce.entity.User;
import com.commerce.repository.UserRepository;
import com.commerce.vo.ResponseVo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by jinwoo on 2017. 6. 22..
 */
@Controller
@Log4j
public class UserController {


    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/join", method= RequestMethod.POST)
    @ResponseBody
    public User join(@RequestBody User user, Model model) {

        ResponseVo responseVo = new ResponseVo();

        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());

        Long lastInsertId = userRepository.save(user).getId();
        User resultUser = userRepository.findOne(lastInsertId);

        return resultUser;
    }

    @RequestMapping(value="/logina", method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo login(@RequestBody String username, @RequestBody String password, Model model) {


        ResponseVo responseVo = new ResponseVo();

        try {
            User findUser = userRepository.findByUsernameAndPassword(username, password);
            responseVo.setResultcode("100");
            if (findUser == null) {
                responseVo.setResultcode("300");
                responseVo.setMessage("Not Found User");
            }
        }
        catch (Exception e) {
            responseVo.setResultcode("400");
            responseVo.setMessage(e.getMessage());
        }

        return responseVo;
    }

    @RequestMapping(value="/logouta")
    public String logout(Model model) {

        return "test";
    }
}
