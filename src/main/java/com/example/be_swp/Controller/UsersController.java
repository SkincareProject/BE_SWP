package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.Request.UserRequest;
import com.example.be_swp.DTOs.Response.UserResponse;
import com.example.be_swp.DTOs.UsersDTO;
import com.example.be_swp.Models.ApiResponse;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = _userService.login(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<UsersDTO> delete(@PathVariable int id){
        UsersDTO usersDTO = _userService.delete(id);

        String status = "";
        String message = "";

        if(usersDTO.getId() == -1){
            status = "404";
            message = "User Not Found!";
        }else{
            status = "200";
            message = "Delete Successfully!";
        }

        usersDTO.setId(id);
        return new ApiResponse<>(status,usersDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UsersDTO> update(@RequestBody UsersDTO usersDTO,@PathVariable int id){
        usersDTO = _userService.update(usersDTO,id);

        String status = "";
        String message = "";

        if(usersDTO.getId() == -1){
            status = "404";
            message = "User Not Found!";
        }else{
            status = "200";
            message = "Update Successfully!";
        }

        usersDTO.setId(id);
        return new ApiResponse<>(status,usersDTO,message);
    }

    @GetMapping("/userGmail")
    public ResponseEntity<UsersDTO> getUserByEmail (@RequestParam String email){
        UsersDTO usersDTO = _userService.getUserByEmail(email);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UsersDTO> getUserById (@PathVariable int id){
        UsersDTO usersDTO = _userService.getById(id);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/userName")
    public ResponseEntity<UsersDTO> getUserByName (@RequestParam String username){
        UsersDTO usersDTO = _userService.getUserByName(username);
        return ResponseEntity.ok(usersDTO);
    }
}
