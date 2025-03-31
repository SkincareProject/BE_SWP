package com.example.be_swp.Controller;

import com.example.be_swp.Bean.ExpertRegisterBean;
import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.DTOs.UsersDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.ExpertRepository;
import com.example.be_swp.Repository.RolesRepository;
import com.example.be_swp.Repository.UsersRepository;
import com.example.be_swp.mapper.experts.ExpertMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experts")
public class ExpertsController {

    @Autowired
    private ExpertRepository _expertRepository;
    @Autowired
    private ExpertMapper _expertMapper;
    @Autowired
    private RolesRepository _rolesRepository;
    @Autowired
    private UsersRepository _usersRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();





    @GetMapping("/findAll")
    public ApiResponse<?> findAll(){
        List<Experts> experts = _expertRepository.findAll();
        List<?> expertsDTOList=_expertMapper.mapToExperts(experts);


        String status = "";
        String message = "";

        return new ApiResponse<>(status, expertsDTOList, message);

    }

    @GetMapping("/findById/{id}")
    public ApiResponse<?> findById(@PathVariable Long id){
        Optional<Experts> expertsDTO = _expertRepository.findById(id);

        String status = "";
        String message = "";


        return new ApiResponse<>(status,expertsDTO,message);
    }

    @PostMapping("/add")
    public ApiResponse<?> add(@RequestBody @Valid ExpertRegisterBean body){
        Users users = new Users();
        Experts experts = new Experts();
        Optional<Roles> role = _rolesRepository.findById(4);

        users.setUsername(body.getUsername());
        users.setFullName(body.getFullName());
        users.setEmail(body.getEmail());
        users.setPhone(body.getPhone());
        users.setPassword(passwordEncoder.encode(body.getPassword()));
        users.setRoles(role.get());
        users.set_active(true);
        users.setCreated_at(LocalDateTime.now());
        users.setUpdated_at(LocalDateTime.now());
        Users newUser=_usersRepository.save(users);

        if(newUser!=null){
            experts.setDescription(body.getDescription());
            experts.setSpecialization(body.getSpecialization());
            experts.setImageBase64(body.getImageBase64());
            experts.setYearOfExperiences(body.getYearOfExperiences());
            experts.setUsers(newUser);
            _expertRepository.save(experts);
        }


        return new ApiResponse<>("200",body,"xxxx");
    }





}
