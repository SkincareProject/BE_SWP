package com.example.be_swp.Service;

import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.UsersRepository;
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
