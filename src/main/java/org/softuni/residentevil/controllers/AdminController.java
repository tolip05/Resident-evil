package org.softuni.residentevil.controllers;

import org.softuni.residentevil.common.annotations.PreAuthenticate;
import org.softuni.residentevil.entities.enums.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @GetMapping("/home")
    @PreAuthenticate(loggedIn = true, inRole = "ADMIN")
    public ModelAndView adminHome(HttpSession session, ModelAndView modelAndView) {
        modelAndView.addObject("username", session.getAttribute("user-username"));

        return this.view("admin-home", modelAndView);
    }
}
