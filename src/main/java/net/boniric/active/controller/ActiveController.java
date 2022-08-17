package net.boniric.active.controller;

import lombok.Data;
import net.boniric.active.config.TestCnxBdd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Controller
public class ActiveController {

    Logger logger = LoggerFactory.getLogger(ActiveController.class); // Logger

    @GetMapping("/login")
    public String home() throws SQLException {
        return "/login";
    }

    // authenticated by login
    private void getUsernamePasswordLoginInfo(Principal user, HttpSession session) {
        assert user instanceof UsernamePasswordAuthenticationToken;
        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if (token.isAuthenticated()) {
            session.setAttribute("name", token.getName());
        }
    }

    @GetMapping("/inscription")
    public String inscription() {
        System.out.println(" Inscription ");
        return "/inscription";
    }


    @GetMapping("/error")
    public String bad() {
        return "/error";
    }

    @GetMapping("/authenticated")
    public String authenticated(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userAuthenticate = auth.getName();
        model.addAttribute("userEmail",userAuthenticate);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        logger.info("Utilisateur : "+userAuthenticate + " authentifié le " + dateFormat.format(date));
        return "/authenticated";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/login";
    }

}