package com.example.be_swp.Controller;

import com.example.be_swp.Models.Services;
import com.example.be_swp.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    ServiceRepository servicesRepository;

    @GetMapping("/getAll")
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    @PostMapping("/save")
    public Services saveService(@RequestBody Services Service) {
        return servicesRepository.save(Service);
    }

    @PutMapping("/update/{id}")
    public Services updateService(@PathVariable long id ,@RequestBody Services Service) {
        Services updatedService = servicesRepository.findById(id).get();
        updatedService.setServiceName(Service.getServiceName());
        updatedService.setPrice(Service.getPrice());
        updatedService.setDuration(Service.getDuration());
        updatedService.setDescription(Service.getDescription());
        updatedService.setType(Service.getType());
        updatedService.setStatus(Service.getStatus());
        return servicesRepository.save(updatedService);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable long id) {
        Services deletedService = servicesRepository.findById(id).get();
        servicesRepository.delete(deletedService);
    }
}
