package com.example.demo.Service;

import com.example.demo.Models.Users;
import com.example.demo.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository _usersRepository;

    public UserService(UsersRepository usersRepository) {
        _usersRepository = usersRepository;
    }

    public List<Users> findAll(){
        return _usersRepository.findAll();
    }

}
