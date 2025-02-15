package com.example.be_swp.Service;

import com.example.be_swp.DTOs.RolesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolesServices {

    private final RolesRepository _rolesRepository;

    public RolesServices(RolesRepository rolesRepository) {
        _rolesRepository = rolesRepository;
    }

    public List<RolesDTO> findAll(){
        List<Roles> rolesList = _rolesRepository.findAll();
        List<RolesDTO> rolesDTOList = new ArrayList<>();
        if (!rolesList.isEmpty()) {
            for (Roles role : rolesList) {
                RolesDTO newRolesDTO = new RolesDTO(role.getId(), role.getName(), role.getDescription());
                rolesDTOList.add(newRolesDTO);
            }
        }
        return rolesDTOList;
    }

    public RolesDTO add(RolesDTO rolesDTO){
        Roles newRole = new Roles();
        newRole.setName(rolesDTO.getName());
        newRole.setDescription(rolesDTO.getDescription());

        String status = "200";
        String message = "Add new role success";

        _rolesRepository.save(newRole);
        rolesDTO.setId(newRole.getId());
        return rolesDTO;
    }

    public Optional<Roles> findById(int id){
        return _rolesRepository.findById(id);
    }

    public boolean update(int id, RolesDTO rolesDTO){

        Optional<Roles> roles = _rolesRepository.findById(id);
        boolean is_OK = false;

        if (roles.isPresent()){
            roles.get().setName(rolesDTO.getName());
            roles.get().setDescription(rolesDTO.getDescription());
            _rolesRepository.save(roles.get());
            is_OK = true;
        }

        return is_OK;
    }

    public RolesDTO delete(int id){
        Optional<Roles> roles = _rolesRepository.findById(id);
        RolesDTO rolesDTO = new RolesDTO();
        if (roles.isPresent()){
            rolesDTO.setId(roles.get().getId());
            rolesDTO.setName(roles.get().getName());
            rolesDTO.setDescription(roles.get().getDescription());
            _rolesRepository.delete(roles.get());
        }else {
            rolesDTO.setId(0);
        }

        return rolesDTO;
    }
}
