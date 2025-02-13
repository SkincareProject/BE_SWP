package com.example.be_swp.Service.Services;

import com.example.be_swp.Exceptions.ServicesNotFoundException;
import com.example.be_swp.Models.Services;
import com.example.be_swp.Repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService implements IServicesService {
    private final ServiceRepository serviceRepository;

    @Override
    public void createService(Services services) {

    }

    @Override
    public Services getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServicesNotFoundException("Service not found"));
    }

    @Override
    public void updateServiceById(Services services, Long id) {

    }

    @Override
    public void deleteServiceById(Long id) {
        serviceRepository.findById(id).ifPresent(serviceRepository::delete);
    }

    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public List<Services> getServicesById(Long id) {
        return serviceRepository.findByServiceId(id);
    }

    @Override
    public List<Services> getServicesByName(String name) {
        return serviceRepository.findByServiceName(name);
    }
}
