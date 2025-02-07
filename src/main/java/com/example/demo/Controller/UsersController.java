package com.example.demo.Controller;

import com.example.demo.Models.Users;
import com.example.demo.Repository.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersRepository _usersRepository;

    public UsersController(UsersRepository usersRepository){

        _usersRepository = usersRepository;

    }

    @GetMapping("/findAll")
    public List<Users> findALl(){
        return _usersRepository.findAll();
    }

}
