package io.khasang.restaurant.controller;

import io.khasang.restaurant.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    private Message message;

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", message.getName());
        return "index";
    }
}
