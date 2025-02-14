package com.example.be_swp.Service;

import com.example.be_swp.DTOs.RolesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServices {

    private final RolesRepository _rolesRepository;

    public RolesServices(RolesRepository rolesRepository) {
        _rolesRepository = rolesRepository;
    }

    public List<Roles> findAll(){
        return _rolesRepository.findAll();
    }

    public void add(Roles roles){
        _rolesRepository.save(roles);
    }

    public Optional<Roles> findById(int id){
        return _rolesRepository.findById(id);
    }

    public ApiResponse<RolesDTO> update(int id, RolesDTO rolesDTO){
        Optional<Roles> roles = _rolesRepository.findById(id);
        String status = "";
        String message = "";
        if (roles.isPresent()){
            status = "200";
            message = "Update Successfully!";
            roles.get().setName(rolesDTO.getName());
            roles.get().setDescription(rolesDTO.getDescription());
            _rolesRepository.save(roles.get());
        }else {
            status = "404";
            message = "Role not found!";
        }

        return new ApiResponse<>(status,rolesDTO,message);
    }

    public ApiResponse<RolesDTO> delete(int id){
        Optional<Roles> roles = _rolesRepository.findById(id);
        RolesDTO rolesDTO = new RolesDTO();
        String status = "";
        String message = "";
        if (roles.isPresent()){
            status = "200";
            message = "Delete Successfully!";
            rolesDTO.setId(roles.get().getId());
            rolesDTO.setName(roles.get().getName());
            rolesDTO.setDescription(roles.get().getDescription());
            _rolesRepository.delete(roles.get());
        }else {
            status = "404";
            message = "Role not found!";
        }

        return new ApiResponse<>(status,rolesDTO,message);
    }
}
