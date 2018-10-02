package org.softuni.residentevil.controllers;

import org.softuni.residentevil.common.annotations.PreAuthenticate;
import org.softuni.residentevil.entities.enums.UserRole;
import org.softuni.residentevil.models.binding.UserLoginBindingModel;
import org.softuni.residentevil.models.binding.UserRegisterBindingModel;
import org.softuni.residentevil.models.service.UserServiceModel;
import org.softuni.residentevil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @PreAuthenticate
    public ModelAndView login() {
        return this.view("login");
    }

    @PostMapping("/login")
    @PreAuthenticate
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel,
                                     HttpSession session) {
        UserServiceModel userFromDb = this.userService
                .getUserByUsername(userLoginBindingModel.getUsername());

        if(userFromDb == null || !userFromDb.getPassword().equals(userLoginBindingModel.getPassword())) {
            return this.view("login");
        }

        session.setAttribute("user-id", userFromDb.getId());
        session.setAttribute("user-username", userFromDb.getUsername());
        session.setAttribute("user-role", userFromDb.getUserRole());

        if(userFromDb.getUserRole() == UserRole.ADMIN) {
            return this.redirect("/admin/home");
        }

        return this.redirect("/home");
    }

    @GetMapping("/register")
    @PreAuthenticate
    public ModelAndView register() {
        return this.view("register");
    }

    @PostMapping("/register")
    @PreAuthenticate
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
        if(!userRegisterBindingModel
                .getConfirmPassword()
                .equals(userRegisterBindingModel.getPassword())) {
            //TODO: Implement me...

            return this.view("register");
        }

        this.userService.createUser(userRegisterBindingModel);

        return this.redirect("/login");
    }

    @GetMapping("/logout")
    @PreAuthenticate(loggedIn = true)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();

        return this.redirect("/");
    }
}
