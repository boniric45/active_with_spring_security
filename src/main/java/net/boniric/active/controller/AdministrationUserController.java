package net.boniric.active.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationUserController {

    @GetMapping("/console")
    public String console(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userAuthenticate = auth.getName();
        model.addAttribute("userEmail",userAuthenticate);
        return "/console";
    }


}
