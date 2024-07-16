package vn.hoidanit.laptopshop.controller;

import java.security.Provider.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    // Dependency injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {

        String test = this.userService.handleHello();
        model.addAttribute("anc", test);
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }
}

// @RestController
// public class UserController {

// private UserService userService;

// // Dependency injection
// public UserController(UserService userService){
// this.userService = userService;
// }

// @GetMapping("")
// public String getHomePage(){
// return this.userService.handleHello();
// }
// }