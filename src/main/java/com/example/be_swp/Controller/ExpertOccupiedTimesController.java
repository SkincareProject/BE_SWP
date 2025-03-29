package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ExpertOccupiedTimesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.ExpertOccupiedTimeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occupiedTime")
public class ExpertOccupiedTimesController {

    private final ExpertOccupiedTimeService _expertOccupiedTimeService;

    public ExpertOccupiedTimesController(ExpertOccupiedTimeService expertOccupiedTimeService) {
        _expertOccupiedTimeService = expertOccupiedTimeService;
    }

//    @GetMapping("/findAll")
//    public ApiResponse<List<ExpertOccupiedTimesDTO>> findAll(){
//        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = _expertOccupiedTimeService.findAll();
//
//        String status = "";
//        String message = "";
//
//        if (expertOccupiedTimesDTOList.isEmpty()){
//            status = "404";
//            message = "No Occupied Time Found!";
//        }else{
//            status = "200";
//            message = "Get all occupied time successfully";
//        }
//
//        return new ApiResponse<>(status,expertOccupiedTimesDTOList,message);
//    }
//
//    @GetMapping("/findByExpertId/{id}")
//    public ApiResponse<List<ExpertOccupiedTimesDTO>> findByExpertId(@PathVariable int id){
//        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = _expertOccupiedTimeService.findByExpertId(id);
//
//        String status = "";
//        String message = "";
//
//        if (expertOccupiedTimesDTOList.isEmpty()){
//            status = "404";
//            message = "No Occupied Time Found With Id: " + id + "!" ;
//        }else{
//            status = "200";
//            message = "Get all occupied time successfully with id: " +id + "!";
//        }
//
//        return new ApiResponse<>(status,expertOccupiedTimesDTOList,message);
//    }
//
//    @GetMapping("/findAllToday")
//    public ApiResponse<List<ExpertOccupiedTimesDTO>> findAllToday(){
//        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = _expertOccupiedTimeService.findAllToday();
//
//        String status = "";
//        String message = "";
//
//        if (expertOccupiedTimesDTOList.isEmpty()){
//            status = "404";
//            message = "No Occupied Time Found For Today!" ;
//        }else{
//            status = "200";
//            message = "Get all occupied time for today successfully!";
//        }
//
//        return new ApiResponse<>(status,expertOccupiedTimesDTOList,message);
//    }

//    @GetMapping("/findAllTodayByExpertId/{id}")
//    public ApiResponse<List<ExpertOccupiedTimesDTO>> findAllTodayByExpertId(@PathVariable int id){
//        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = _expertOccupiedTimeService.findAllTodayByExpertId(id);
//
//        String status = "";
//        String message = "";
//
//        if (expertOccupiedTimesDTOList.isEmpty()){
//            status = "404";
//            message = "No Occupied Time Found For Today With Id: " + id + "!" ;
//        }else{
//            status = "200";
//            message = "Get all occupied time for today successfully with id: " +id + "!";
//        }
//
//        return new ApiResponse<>(status,expertOccupiedTimesDTOList,message);
//    }

    @PostMapping
    public ApiResponse<String> Post (){
        return new ApiResponse<>("Not Sure","What To Do","Here Yet");
    }

    @PutMapping
    public ApiResponse<String> Put (){
        return new ApiResponse<>("Not Sure","What To Do","Here Yet");
    }

    @DeleteMapping
    public ApiResponse<String> Delete (){
        return new ApiResponse<>("Not Sure","What To Do","Here Yet");
    }

}
