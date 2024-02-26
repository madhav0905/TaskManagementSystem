package com.TaskManagement.TaskMangement.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        return "login";
    }

    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }
    @GetMapping("/")
    public String mainPage()
    {
        return "redirect:/api/v1/mytasks";
    }
}