package com.example.be_swp.Service;


import com.example.be_swp.DTOs.Request.UserRequest;
import com.example.be_swp.DTOs.Response.UserResponse;

import com.example.be_swp.DTOs.UsersDTO;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.RolesRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private final UsersRepository _usersRepository;

    @Autowired
    private  RolesRepository _rolesRepository;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

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
        return ("Register successfully!!!");


    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return _usersRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found!!!"));
    }




    public Users getUserByEmail(String email){
        return _usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("NOT FOUND THE USER!!"));
    }

    public UserResponse login(UserRequest userRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getPassword()
                    )
            );
        }catch (Exception e){
            throw new NullPointerException("Wrong username or password!!!");
        }
        Users users = _usersRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = tokenService.generateToken(users);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(users.getEmail());
        userResponse.setId(users.getId());
        userResponse.setFullName(users.getFullName());
        userResponse.setUsername(users.getUsername());
        userResponse.setUsername(users.getUsername());
        userResponse.setRole_id(users.getRoles().getId());
        userResponse.setToken(token);

        return  userResponse;
    }
}
