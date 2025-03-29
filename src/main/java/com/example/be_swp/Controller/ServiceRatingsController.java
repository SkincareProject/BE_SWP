package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ServiceRatingsDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.ServiceRatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serviceRatings")
public class ServiceRatingsController {

//    private final ServiceRatingService _serviceRatingService;
//
//    public ServiceRatingsController(ServiceRatingService _serviceRatingService) {
//        this._serviceRatingService = _serviceRatingService;
//    }
//
//    @GetMapping("/findAll")
//    public ApiResponse<List<ServiceRatingsDTO>> findAll(){
//        List<ServiceRatingsDTO> serviceRatingsDTOList = _serviceRatingService.findAll();
//        String status = "";
//        String message = "";
//
//        if (serviceRatingsDTOList.isEmpty()){
//            status = "404";
//            message = "No Ratings Found!";
//        } else{
//            status = "200";
//            message = "Got All Services Ratings Successfully!";
//        }
//        return new ApiResponse<>(status,serviceRatingsDTOList,message);
//    }
//
//    @GetMapping("/findById/{id}")
//    public ApiResponse<ServiceRatingsDTO> findById(@PathVariable int id){
//        ServiceRatingsDTO serviceRatingsDTO = _serviceRatingService.findById(id);
//        String status = "";
//        String message = "";
//
//        if (serviceRatingsDTO.getServiceRatingId() == -1){
//            status = "404";
//            message = "Rating Not Found With ID: " + id + "!";
//        } else{
//            status = "200";
//            message = "Find Rating Successfully With ID: " + id + "!";
//        }
//        serviceRatingsDTO.setServiceRatingId(id);
//
//        return new ApiResponse<>(status,serviceRatingsDTO,message);
//    }
//
//    @GetMapping("/findByServiceId/{serviceId}")
//    public ApiResponse<ServiceRatingsDTO> findByServiceId(@PathVariable int serviceId){
//        ServiceRatingsDTO serviceRatingsDTO = _serviceRatingService.findByServiceId(serviceId);
//
//        String status = "";
//        String message = "";
//
//        if (serviceRatingsDTO.getServicesId() == -1){
//            status = "404";
//            message = "Rating Not Found With ID: " + serviceId + "!";
//        }else{
//            status = "200";
//            message = "Find Rating Successfully With ID: " + serviceId + "!";
//        }
//        serviceRatingsDTO.setServicesId(serviceId);
//
//        return new ApiResponse<>(status,serviceRatingsDTO,message);
//    }
//
//    @PostMapping("/add")
//    public ApiResponse<ServiceRatingsDTO> add(@RequestBody ServiceRatingsDTO serviceRatingsDTO){
//        serviceRatingsDTO = _serviceRatingService.add(serviceRatingsDTO);
//
//        String status = "";
//        String message = "";
//
//        switch (serviceRatingsDTO.getServicesId()){
//            case -1:
//                status = "404";
//                message = "User Not Found!";
//                break;
//            case -2:
//                status = "404";
//                message = "Service Not Found";
//                break;
//            case -3:
//                status = "404";
//                message = "Appointment Not Found!";
//                break;
//            case -4:
//                status = "400";
//                message = "Invalid Rating!";
//                break;
//            default:
//                status = "200";
//                message = "Add Expert Rating Successfully!";
//                break;
//        }
//        return new ApiResponse<>(status,serviceRatingsDTO,message);
//    }
//
//    @PutMapping("update/{id}")
//    public ApiResponse<ServiceRatingsDTO> update(@RequestBody ServiceRatingsDTO serviceRatingsDTO, @PathVariable int id){
//        // prevent changing id
//        int originalId = serviceRatingsDTO.getServicesId();
//        serviceRatingsDTO = _serviceRatingService.update(serviceRatingsDTO, id);
//
//        String status = "";
//        String message = "";
//
//        switch (serviceRatingsDTO.getServicesId()){
//            case -1:
//                status = "404";
//                message = "Rating Not Found!";
//                break;
//            case -4:
//                status = "400";
//                message = "Invalid Rating!";
//                break;
//            default:
//                status = "200";
//                message = "Update Expert Rating Successfully!";
//                break;
//        }
//
//        serviceRatingsDTO.setServicesId(originalId);
//
//        return new ApiResponse<>(status,serviceRatingsDTO,message);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ApiResponse<ServiceRatingsDTO> delete(@PathVariable int id){
//        ServiceRatingsDTO serviceRatingsDTO = _serviceRatingService.delete(id);
//
//        String status = "";
//        String message = "";
//
//        if (serviceRatingsDTO.getServicesId() == -1){
//            status = "404";
//            message = "Rating Not Found With ID: " + id + "!";
//        }else{
//            status = "200";
//            message = "Delete Successfully!";
//        }
//
//        serviceRatingsDTO.setServicesId(id);
//
//        return new ApiResponse<>(status,serviceRatingsDTO,message);
//    }
}
