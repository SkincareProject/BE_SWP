package com.example.be_swp.Controller;

import com.example.be_swp.Models.Users;
import com.example.be_swp.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService _userService;

    public UsersController(UserService userService){
        _userService = userService;

    }

    @GetMapping("/findAll")
    public List<Users> findALl(){
        return _userService.findAll();
    }

}
