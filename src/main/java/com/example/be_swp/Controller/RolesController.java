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
        List<RolesDTO> rolesDTOList = _rolesServices.findAll();

        String status = "";
        String message = "";

        if (rolesDTOList.isEmpty()){
            status = "404";
            message = "No Role Found";
        }else{
            status = "200";
            message = "Get all Roles successfully";
        }

        return new ApiResponse<>(status,rolesDTOList,message);

    }

    @PostMapping("/add")
    public ApiResponse<RolesDTO> add(@RequestBody RolesDTO rolesDTO){

        rolesDTO = _rolesServices.add(rolesDTO);

        String status = "200";
        String message = "Add new role successfully!";

        return new ApiResponse<>(status,rolesDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<RolesDTO> update(@RequestBody RolesDTO rolesDTO, @PathVariable int id){
        boolean is_OK = _rolesServices.update(id, rolesDTO);

        String status = "";
        String message = "";

        if (!is_OK){
            status = "404";
            message = "No Role Found!";
        }else{
            status = "200";
            message = "Update Successfully!";
        }

        rolesDTO.setId(id);

        return new ApiResponse<>(status,rolesDTO,message);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<RolesDTO> delete(@PathVariable int id){
        RolesDTO rolesDTO = _rolesServices.delete(id);

        String status = "";
        String message = "";

        if (rolesDTO.getId() == 0){
            status = "404";
            message = "No Role Found!";
        }else{
            status = "200";
            message = "Delete Successfully!";
        }

        rolesDTO.setId(id);

        return new ApiResponse<>(status,rolesDTO,message);
    }


}
