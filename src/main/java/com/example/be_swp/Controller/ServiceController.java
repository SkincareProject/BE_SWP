package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ServicesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.ServicesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServicesService _servicesService;

    public ServiceController(ServicesService _servicesService) {
        this._servicesService = _servicesService;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<ServicesDTO>> getAll() {
        List<ServicesDTO> servicesDTOList = _servicesService.findAll();
        String status = "";
        String message = "";
        if (servicesDTOList.isEmpty()) {
            status = "404";
            message = "Service Not Found!";
        } else {
            status = "200";
            message = "Service Found!";
        }
        return new ApiResponse<>(status,servicesDTOList,message);
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<ServicesDTO> findById(@PathVariable int id){
        ServicesDTO servicesDTO = _servicesService.findById(id);

        String status = "";
        String message = "";

        if (servicesDTO.getServiceId() == -1){
            status = "404";
            message = "Service Not Found!";
        } else {
            status = "200";
            message = "Service Found!";
        }

        servicesDTO.setServiceId(id);

        return new ApiResponse<>(status,servicesDTO,message);
    }

    @PostMapping("/add")
    public ApiResponse<ServicesDTO> add(@RequestBody ServicesDTO servicesDTO) {
        servicesDTO = _servicesService.add(servicesDTO);
        String status = "200";
        String message = "Saved Service!";
        return new ApiResponse<>(status,servicesDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<ServicesDTO> update(@PathVariable int id,@RequestBody ServicesDTO servicesDTO) {
        servicesDTO = _servicesService.update(servicesDTO,id);
        String status = "";
        String message = "";

        if (servicesDTO.getServiceId() == -1){
            status = "404";
            message = "Service Not Found!";
        } else {
            status = "200";
            message = "Service Updated!";
        }

        servicesDTO.setServiceId(id);

        return new ApiResponse<>(status,servicesDTO,message);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<ServicesDTO> delete(@PathVariable int id) {
        ServicesDTO servicesDTO = _servicesService.delete(id);
        String status = "";
        String message = "";

        if (servicesDTO.getServiceId() == -1){
            status = "404";
            message = "Service Not Found!";
        } else {
            status = "200";
            message = "Service Deleted!";
        }
        servicesDTO.setServiceId(id);

        return new ApiResponse<>(status,servicesDTO,message);

    }
}
