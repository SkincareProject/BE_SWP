package com.example.be_swp.Service;

import com.example.be_swp.DTOs.UsersDTO;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.RolesRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UsersRepository _usersRepository;

    @Autowired
    private  RolesRepository _rolesRepository;

    public UserService(UsersRepository usersRepository) {
        _usersRepository = usersRepository;
    }

    public List<UsersDTO> findAll(){
        List<Users> usersList = _usersRepository.findAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();
        if(!usersList.isEmpty()){
            for(Users users: usersList){
                UsersDTO usersDTO = new UsersDTO(users.getUsername(), users.getPassword(), users.getFullName(),  users.getEmail(), users.getPhone(), users.is_active()
                , users.getRoles().getId(), users.getCreated_at(), users.getUpdated_at());
                usersDTOList.add(usersDTO);
            }
        }

        return usersDTOList;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(UsersDTO usersDTO){
        if(_usersRepository.findByEmail(usersDTO.getEmail()).isPresent()){
            return "Email is exit !!!";
        }
        Optional<Roles> role = _rolesRepository.findById(2);


        Users users = new Users();
        users.setUsername(usersDTO.getUsername());
        users.setFullName(usersDTO.getFullName());
        users.setEmail(usersDTO.getEmail());
        users.setPhone(usersDTO.getPhone());
        users.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        users.setRoles(role.get());
        users.setCreated_at(LocalDateTime.now());
        users.setUpdated_at(LocalDateTime.now());

        _usersRepository.save(users);
        return ("Login successfully!!!");


    }

    public Users getUserByEmail(String email){
        return _usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("NOT FOUND THE USER!!"));
    }

}
