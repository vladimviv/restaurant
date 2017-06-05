package io.khasang.restaurant.controller;

import io.khasang.restaurant.model.Cat;
import io.khasang.restaurant.model.Message;
import io.khasang.restaurant.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private Message message;
    @Autowired
    private Cat cat;
    @Autowired
    private SpellService spellService;

    @RequestMapping("/")
    public String hello() {
        return "document";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTable(Model model) {
        model.addAttribute("create", cat.createCatTable());
        return "create";
    }

    @RequestMapping("/list")
    public String getList(Model model) {
        List<Message> messagesList = new ArrayList<>();
        messagesList.add(new Message(3, "Cat"));
        messagesList.add(new Message(2, "Dog"));
        model.addAttribute("list", messagesList);
        return "list";
    }

    @RequestMapping("/user/page")
    public String getUser() {
        return "page";
    }

    @RequestMapping("/admin/page")
    public String getAdmin() {
        return "admin";
    }

    @RequestMapping(value = {"/password/{password}"}, method = RequestMethod.GET)
    public ModelAndView passwordEncode(@PathVariable("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("password");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(password));
        return modelAndView;
    }

    @RequestMapping(value = "/speller/{word}", method = RequestMethod.GET)
    public String checkWordSpeller(@PathVariable("word") String word, Model model) throws MalformedURLException {
        model.addAttribute("answer", spellService.checkword(word));
        return "speller";
    }

}
