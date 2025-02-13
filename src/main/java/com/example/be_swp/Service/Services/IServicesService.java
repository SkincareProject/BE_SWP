package com.example.be_swp.Service.Services;

import com.example.be_swp.Models.Services;

import java.util.List;

public interface IServicesService {
    void createService(Services services);
    Services getServiceById(Long id);
    void updateServiceById(Services services, Long id);
    void deleteServiceById(Long id);
    List<Services> getAllServices();
    List<Services> getServicesById(Long id);
    List<Services> getServicesByName(String name);
}
