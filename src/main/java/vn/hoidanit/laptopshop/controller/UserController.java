package vn.hoidanit.laptopshop.controller;


import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

    private UserService userService;


    // Dependency injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List <User> arrUser = this.userService.getUserByEmail("loc@gmail.com");
        System.out.println(arrUser);
        model.addAttribute("anc", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }
    @RequestMapping("/admin/user/create")
    public String CreateUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/table-user";
    }
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/user-detail";
    }
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model,  @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model,@ModelAttribute("newUser") User hoidanit) {
        User currentUser = this.userService.getUserById(hoidanit.getId());
        //model.addAttribute("newUser", currentUser);
        if (currentUser != null) {
            //System.out.println("print here");
            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model,  @PathVariable long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }
    @PostMapping("/admin/user/delete")
    public String postDeleteUserPage(Model model  ,@ModelAttribute("newUser") User eric) {
        this.userService.deleteAUser(eric.getId());
        return "redirect:/admin/user";
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