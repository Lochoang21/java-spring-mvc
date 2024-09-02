package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model){
        model.addAttribute("countUsers", this.userService.countUser());
        model.addAttribute("countProducts", this.userService.countProduct());
        model.addAttribute("countOrders", this.userService.countOrder());
        return "admin/dashboard/show" ;
    }
}
