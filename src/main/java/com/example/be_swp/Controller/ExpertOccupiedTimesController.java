package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ExpertOccupiedTimesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.ExpertOccupiedTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/occupiedTime")
public class ExpertOccupiedTimesController {

    private final ExpertOccupiedTimeService _expertOccupiedTimeService;

    public ExpertOccupiedTimesController(ExpertOccupiedTimeService expertOccupiedTimeService) {
        _expertOccupiedTimeService = expertOccupiedTimeService;
    }

    @GetMapping("/findAll")
    public ApiResponse<List<ExpertOccupiedTimesDTO>> findAll(){
        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = _expertOccupiedTimeService.findAll();

        String status = "";
        String message = "";

        if (expertOccupiedTimesDTOList.isEmpty()){
            status = "404";
            message = "No Occupied Time Found!";
        }else{
            status = "200";
            message = "Get all occupied time successfully";
        }

        return new ApiResponse<>(status,expertOccupiedTimesDTOList,message);
    }

    @GetMapping("/findByExpertId/{id}")
    public ApiResponse<List<ExpertOccupiedTimesDTO>> findByExpertId(@PathVariable int id){
        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = _expertOccupiedTimeService.findByExpertId(id);

        String status = "";
        String message = "";

        if (expertOccupiedTimesDTOList.isEmpty()){
            status = "404";
            message = "No Occupied Time Found With Id: " + id + "!" ;
        }else{
            status = "200";
            message = "Get all occupied time successfully with id: " +id + "!";
        }

        return new ApiResponse<>(status,expertOccupiedTimesDTOList,message);
    }

}
