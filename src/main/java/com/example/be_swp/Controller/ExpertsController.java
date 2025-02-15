package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.DTOs.UsersDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.UserExpertDTO;
import com.example.be_swp.Service.ExpertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experts")
public class ExpertsController {

    private final ExpertService _expertService;

    public ExpertsController(ExpertService expertService){
        _expertService = expertService;
    }

    @GetMapping("/findAll")
    public ApiResponse<List<ExpertsDTO>> findAll(){
        List<ExpertsDTO> expertsDTOList = _expertService.findAll();

        String status = "";
        String message = "";

        if (!expertsDTOList.isEmpty()){
            status = "200";
            message = "Get all Experts successfully!";
        }else {
            status = "404";
            message = "No Experts Found!";
        }

        return new ApiResponse<>(status,expertsDTOList,message);

    }

    @GetMapping("/findById/{id}")
    public ApiResponse<ExpertsDTO> findById(@PathVariable int id){
        ExpertsDTO expertsDTO = _expertService.findById(id);

        String status = "";
        String message = "";

        if (expertsDTO.getExpertId() == -1){
            status = "404";
            message = "Expert Not Found!";
        }else {
            status = "200";
            message = "Expert Found!";
        }

        expertsDTO.setExpertId(id);

        return new ApiResponse<>(status,expertsDTO,message);
    }

    @GetMapping("findByName")
    public ApiResponse<List<ExpertsDTO>> findByName(@RequestParam String name){
        List<ExpertsDTO> expertsDTOList = _expertService.findByName(name);


        String status = "";
        String message = "";

        if (expertsDTOList.isEmpty()){
            status = "404";
            message = "Experts Not Found!";
        }else {
            status = "200";
            message = "Experts Found!";
        }

        return new ApiResponse<>(status,expertsDTOList,message);
    }

    @PostMapping("/add")
    public ApiResponse<ExpertsDTO> add(@RequestBody UserExpertDTO userExpertDTO){
        ExpertsDTO expertsDTO = _expertService.add(userExpertDTO);

        String status = "";
        String message = "";

        status = "200";
        message = "Add expert successfully!";

        return new ApiResponse<>(status,expertsDTO,message);

    }

    @PutMapping("/update/{id}")
    public ApiResponse<ExpertsDTO> update(@RequestBody ExpertsDTO expertsDTO,@PathVariable int id){

        expertsDTO = _expertService.update(expertsDTO,id);

        String status = "";
        String message = "";

        if (expertsDTO.getExpertId() == -1){
            status = "404";
            message = "Expert Not Found!";
        }else{
            status = "200";
            message = "Update Successfully!";
        }

        return new ApiResponse<>(status,expertsDTO,message);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<ExpertsDTO> delete(@PathVariable int id){

        ExpertsDTO expertsDTO = _expertService.delete(id);

        String status = "";
        String message = "";

        if (expertsDTO.getExpertId() == -1){
            status = "404";
            message = "Expert Not Found!";
        }else{
            status = "200";
            message = "Delete Successfully!";
        }

        return new ApiResponse<>(status,expertsDTO,message);
    }

}
