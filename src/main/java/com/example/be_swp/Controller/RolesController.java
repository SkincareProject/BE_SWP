package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.RolesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Service.RolesServices;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesServices _rolesServices;

    public RolesController(RolesServices rolesServices){
        _rolesServices = rolesServices;
    }

    @GetMapping("/findAll")
    public ApiResponse<List<RolesDTO>> findAll(){
        List<Roles> rolesList = _rolesServices.findAll();
        List<RolesDTO> rolesDTOList = new ArrayList<>();
        String status = "200";
        String message = "Get all Roles";
        if (rolesList.isEmpty()){
            status = "404";
            message = "Not Found";
        }else{
            for (Roles role: rolesList){
                RolesDTO neRolesDTO = new RolesDTO(role.getId(),role.getName(),role.getDescription());
                rolesDTOList.add(neRolesDTO);
            }
        }
        return new ApiResponse<>(status,rolesDTOList,message);
    }

    @PostMapping("/add")
    public ApiResponse<RolesDTO> add(@RequestBody RolesDTO rolesDTO){
        Roles newRole = new Roles();
        newRole.setName(rolesDTO.getName());
        newRole.setDescription(rolesDTO.getDescription());

        String status = "200";
        String message = "Add new role success";

        _rolesServices.add(newRole);
        rolesDTO.setId(newRole.getId());
        return new ApiResponse<>(status,rolesDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<RolesDTO> update(@RequestBody RolesDTO rolesDTO, @PathVariable int id){
        return _rolesServices.update(id, rolesDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<RolesDTO> delete(@PathVariable int id){
        return _rolesServices.delete(id);
    }


}
