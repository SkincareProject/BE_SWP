package com.example.be_swp.Controller;


import com.example.be_swp.DTOs.ExpertRatingDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.ExpertRatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expertRatings")
public class ExpertRatingsController {

    private final ExpertRatingService _expertRatingService;

    public ExpertRatingsController(ExpertRatingService expertRatingService){
        _expertRatingService = expertRatingService;
    }

    @GetMapping("/findAll")
    public ApiResponse<List<ExpertRatingDTO>> findAll(){
        List<ExpertRatingDTO> expertRatingDTOList = _expertRatingService.findAll();

        String status = "";
        String message = "";

        if (expertRatingDTOList.isEmpty()){
            status = "404";
            message = "Not Ratings Found!";
        }else{
            status = "200";
            message = "Get All Expert Ratings Successfully!";
        }

        return new ApiResponse<>(status,expertRatingDTOList,message);
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<ExpertRatingDTO> findById(@PathVariable int id){
        ExpertRatingDTO expertRatingDTO = _expertRatingService.findById(id);

        String status = "";
        String message = "";

        if (expertRatingDTO.getExpertRatingId() == -1){
            status = "404";
            message = "Rating Not Found With ID: " + id + "!";
        }else{
            status = "200";
            message = "Find Rating Successfully With ID: " + id + "!";
        }

        expertRatingDTO.setExpertRatingId(id);

        return new ApiResponse<>(status,expertRatingDTO,message);
    }

    @GetMapping("/findByExpertId/{expertId}")
    public ApiResponse<ExpertRatingDTO> findByExpertId(@PathVariable int expertId){
        ExpertRatingDTO expertRatingDTO = _expertRatingService.findByExpertId(expertId);

        String status = "";
        String message = "";

        if (expertRatingDTO.getExpertRatingId() == -1){
            status = "404";
            message = "Rating Not Found With ID: " + expertId + "!";
        }else{
            status = "200";
            message = "Find Rating Successfully With ID: " + expertId + "!";
        }

        expertRatingDTO.setExpertId(expertId);

        return new ApiResponse<>(status,expertRatingDTO,message);
    }

    @PostMapping("/add")
    public ApiResponse<ExpertRatingDTO> add(@RequestBody ExpertRatingDTO expertRatingDTO){
        int id = expertRatingDTO.getExpertRatingId();
        expertRatingDTO = _expertRatingService.add(expertRatingDTO);

        String status = "";
        String message = "";

        switch (expertRatingDTO.getExpertRatingId()){
            case -1:
                status = "404";
                message = "Customer Not Found!";
                break;
            case -2:
                status = "404";
                message = "Expert Not Found";
                break;
            case -3:
                status = "404";
                message = "Appointment Not Found!";
                break;
            case -4:
                status = "400";
                message = "Invalid Rating!";
                break;
            default:
                status = "200";
                message = "Add Expert Rating Successfully!";
                break;
        }

        return new ApiResponse<>(status,expertRatingDTO,message);
    }

    @PutMapping("update/{id}")
    public ApiResponse<ExpertRatingDTO> update(@RequestBody ExpertRatingDTO expertRatingDTO, @PathVariable int id){
        int oId = expertRatingDTO.getExpertRatingId();
        expertRatingDTO = _expertRatingService.update(expertRatingDTO, id);

        String status = "";
        String message = "";

        switch (expertRatingDTO.getExpertRatingId()){
            case -1:
                status = "404";
                message = "Rating Not Found!";
                break;
            case -4:
                status = "400";
                message = "Invalid Rating!";
                break;
            default:
                status = "200";
                message = "Update Expert Rating Successfully!";
                break;
        }

        expertRatingDTO.setExpertRatingId(oId);

        return new ApiResponse<>(status,expertRatingDTO,message);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<ExpertRatingDTO> delete(@PathVariable int id){
        ExpertRatingDTO expertRatingDTO = _expertRatingService.delete(id);

        String status = "";
        String message = "";

        if (expertRatingDTO.getExpertRatingId() == -1){
            status = "404";
            message = "Rating Not Found With ID: " + id + "!";
        }else{
            status = "200";
            message = "Delete Successfully!";
        }

        expertRatingDTO.setExpertRatingId(id);

        return new ApiResponse<>(status,expertRatingDTO,message);
    }
}
