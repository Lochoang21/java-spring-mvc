package vn.hoidanit.laptopshop.controller;


import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        model.addAttribute("anc", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {

        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        System.out.println("Rung here" + hoidanit);
        this.userService.handleSaveUser(hoidanit);
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