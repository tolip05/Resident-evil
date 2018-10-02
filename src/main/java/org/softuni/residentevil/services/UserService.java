package org.softuni.residentevil.services;

import org.softuni.residentevil.models.binding.UserRegisterBindingModel;
import org.softuni.residentevil.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    boolean createUser(UserRegisterBindingModel bindingModel);

    UserServiceModel getUserByUsername(String username);

    List<UserServiceModel> getAll();
}
