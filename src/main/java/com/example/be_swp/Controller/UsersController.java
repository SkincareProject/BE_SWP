package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.UsersDTO;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService _userService;

    public UsersController(UserService userService){
        _userService = userService;

    }

    @GetMapping("/findAll")
    public List<UsersDTO> findALl(){
        return _userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsersDTO usersDTO){
        String respone = _userService.registerUser(usersDTO);
        return ResponseEntity.ok(respone);
    }

    @GetMapping("/userGmail")
    public ResponseEntity<Users> getUserByEmail (@RequestParam String email){
        Users users = _userService.getUserByEmail(email);
        return ResponseEntity.ok(users);
    }

}
