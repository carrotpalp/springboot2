package com.springboot2.carrot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot2.carrot.model.Person;
import com.springboot2.carrot.model.User;
import com.springboot2.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;

//    @ModelAttribute
//    public void modelAttribute(@PathVariable String userName, Model model){
//        model.addAttribute("user",userService.getUser(userName));
//        System.out.println("set modelAttribute end!!!"+userName);
//    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/index.btl");
        return modelAndView;
    }

    @GetMapping("/getUserName")
    public @ResponseBody User getUser(String userName){
        return userService.getUser(userName);
    }

    @PostMapping(path = "/update.json",params = "action=save")
    @ResponseBody
    public String saveUser(){
        System.out.println("call save");
        return "call save";
    }

    @GetMapping(path = "/user/{userName}")
    public User getUser1(@PathVariable String userName){
        return userService.getUser(userName);
    }

    @GetMapping(path = "/userObject")
    public @ResponseBody Object getUser2(@Valid User user, BindingResult results){
        if(results.hasErrors()){
            return results.getFieldError().getDefaultMessage();
        }
        return user;
    }

    @GetMapping(path = "/userObject2")
    public @ResponseBody Object getUser3(@Validated({User.Update.class}) User user, BindingResult results){
        if(results.hasErrors()){
            List<FieldError> fieldErrors = results.getFieldErrors();
            return fieldErrors;
        }
        return user;
    }

//    @InitBinder
//    private void initBinder(WebDataBinder binder){
//        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
//    }

    @GetMapping(path = "/date")
    public void print(Date d){
        System.out.println("@InitBinder:"+d);
    }

    @GetMapping(path = "/addJson.json")
    @ResponseBody
    public Person getJson(){
        Person person = null;
//        String json = "{\"name\":\"aaa\",\"age\":1,\"sex\":22}";
//        String json = "{\"name\":\"aaa\",\"age\":1,\"sex\":22,\"height\":\"165cm\"}";
        String json = "{\"name\":\"aaa\",\"age\":1}";
        try {
            person = mapper.readValue(json, Person.class);
            System.out.println(mapper.writeValueAsString(person));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

}
