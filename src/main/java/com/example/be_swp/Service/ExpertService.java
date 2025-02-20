package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.DTOs.UserExpertDTO;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.ExpertRepository;
import com.example.be_swp.Repository.RolesRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertService {

    private final ExpertRepository _expertRepository;
    private final UsersRepository _userRepository;
    private final RolesRepository _rolesRepository;

    public ExpertService(ExpertRepository expertRepository, UsersRepository usersRepository, RolesRepository rolesRepository){
        _expertRepository = expertRepository;
        _userRepository = usersRepository;
        _rolesRepository = rolesRepository;
    }

    public List<ExpertsDTO> findAll(){
        List<Experts> expertsList = _expertRepository.findAll();
        List<ExpertsDTO> expertsDTOList = new ArrayList<>();
        if (!expertsList.isEmpty()){
            for (Experts expert: expertsList){
                ExpertsDTO expertsDTO = new ExpertsDTO(expert.getExpertId(), expert.getUsers().getId(), expert.getSpecialization(), expert.getYearOfExperiences(), expert.getDescription(), expert.getStatus(), expert.getCreated_at(), expert.getUpdated_at());
                expertsDTOList.add(expertsDTO);
            }
        }

        return expertsDTOList;
    }

    public ExpertsDTO findById(int id){
        ExpertsDTO expertsDTO = new ExpertsDTO();
        Optional<Experts> optionalExperts = _expertRepository.findById(id);
        if (optionalExperts.isPresent()){

            Experts experts = optionalExperts.get();
            expertsDTO = new ExpertsDTO(experts.getExpertId(),experts.getUsers().getId(),experts.getSpecialization(),experts.getYearOfExperiences(),experts.getDescription(),experts.getStatus(),experts.getCreated_at(),experts.getUpdated_at());

        }else{
            expertsDTO.setExpertId(-1);
        }

        return expertsDTO;
    }

    public List<ExpertsDTO> findByName(String name){
        List<Experts> expertsList = _expertRepository.findByName(name);
        List<ExpertsDTO> expertsDTOList = new ArrayList<>();

        if (!expertsList.isEmpty()){
            for (Experts expert: expertsList){
                ExpertsDTO expertsDTO = new ExpertsDTO(expert.getExpertId(),expert.getUsers().getId(),expert.getSpecialization(), expert.getYearOfExperiences(), expert.getDescription(), expert.getStatus(), expert.getCreated_at(),expert.getUpdated_at());
                expertsDTOList.add(expertsDTO);
            }
        }

        return expertsDTOList;
    }

    public ExpertsDTO add(UserExpertDTO userExpertDTO){
        Users users = new Users(userExpertDTO.getUsersDTO().getUsername(),userExpertDTO.getUsersDTO().getPassword(),userExpertDTO.getUsersDTO().getFullName(),userExpertDTO.getUsersDTO().getEmail(),userExpertDTO.getUsersDTO().getPhone(),true,LocalDateTime.now(),LocalDateTime.now());

        Experts experts = new Experts(userExpertDTO.getExpertsDTO().getSpecialization(),userExpertDTO.getExpertsDTO().getYearOfExperiences(),userExpertDTO.getExpertsDTO().getDescription(),1,LocalDateTime.now(),LocalDateTime.now());

        Optional<Roles> roles = _rolesRepository.findById(4);

        users.setRoles(roles.get());
        users.setExperts(experts);

        experts.setUsers(users);

        _userRepository.save(users);

        userExpertDTO.getExpertsDTO().setExpertId(experts.getExpertId());
        userExpertDTO.getExpertsDTO().setUserId(experts.getUsers().getId());

        return userExpertDTO.getExpertsDTO();
    }

    public ExpertsDTO update(ExpertsDTO expertsDTO, int id){
        Optional<Experts> optionalExperts = _expertRepository.findById(id);

        if (optionalExperts.isEmpty()){
            expertsDTO.setExpertId(-1);
        }else {
            Experts experts = optionalExperts.get();
            experts.setSpecialization(expertsDTO.getSpecialization());
            experts.setYearOfExperiences(expertsDTO.getYearOfExperiences());
            experts.setDescription(expertsDTO.getDescription());
            experts.setUpdated_at(LocalDateTime.now());

            _expertRepository.save(experts);

            expertsDTO.setExpertId(experts.getExpertId());
            expertsDTO.setExpertId(experts.getUsers().getId());
            expertsDTO.setStatus(experts.getStatus());
            experts.setCreated_at(experts.getCreated_at());
            expertsDTO.setUpdated_at(experts.getUpdated_at());

        }
        return expertsDTO;
    }

    public ExpertsDTO delete(int id){
        Optional<Experts> optionalExperts = _expertRepository.findById(id);
        ExpertsDTO expertsDTO = new ExpertsDTO();

        if (optionalExperts.isEmpty()){
            expertsDTO.setExpertId(-1);
        }else{
            Experts experts = optionalExperts.get();
            expertsDTO = new ExpertsDTO(experts.getExpertId(),experts.getUsers().getId(),experts.getSpecialization(),experts.getYearOfExperiences(),experts.getDescription(),experts.getStatus(),experts.getCreated_at(),experts.getUpdated_at());

            Optional<Users> optionalUsers = _userRepository.findById(experts.getUsers().getId());

            _expertRepository.delete(experts);
            _userRepository.delete(optionalUsers.get());

        }
        return expertsDTO;
    }


}
