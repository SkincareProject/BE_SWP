package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ServicesDTO;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {
    private final ServicesRepository _servicesRepository;

    public ServicesService(ServicesRepository _servicesRepository) {
        this._servicesRepository = _servicesRepository;
    }

    public List<ServicesDTO> findAll(){
        List<Services> servicesList = _servicesRepository.findAll();
        List<ServicesDTO> servicesDTOList = new ArrayList<>();
        if (!servicesList.isEmpty()){
            for (Services services: servicesList){
                ServicesDTO servicesDTO = new ServicesDTO(services.getServiceId(),services.getServiceName()
                        ,services.getPrice(),services.getDescription(),services.getDuration(),services.getStatus()
                        ,services.getType(),services.getSkinType(),services.getCreated_at(),services.getUpdated_at());
                servicesDTOList.add(servicesDTO);
            }
        }
        return servicesDTOList;
    }

    public ServicesDTO findById(int id){
        ServicesDTO servicesDTO = new ServicesDTO();
        Optional<Services> optionalServices = _servicesRepository.findById(id);
        if (optionalServices.isPresent()){
            Services services = optionalServices.get();
            servicesDTO = new ServicesDTO(services.getServiceId(),services.getServiceName()
                    ,services.getPrice(),services.getDescription(),services.getDuration(),services.getStatus()
                    ,services.getType(),services.getSkinType(),services.getCreated_at(),services.getUpdated_at());
        }else{
            servicesDTO.setServiceId(-1);
        }
        return servicesDTO;
    }

    public ServicesDTO add(ServicesDTO servicesDTO){
        Services newServices = new Services();
        newServices.setServiceName(servicesDTO.getServiceName());
        newServices.setPrice(servicesDTO.getPrice());
        newServices.setDescription(servicesDTO.getDescription());
        newServices.setDuration(servicesDTO.getDuration());
        newServices.setStatus(servicesDTO.getStatus());
        newServices.setType(servicesDTO.getType());
        newServices.setCreated_at(LocalDateTime.now());
        newServices.setUpdated_at(LocalDateTime.now());

        _servicesRepository.save(newServices);
        servicesDTO.setServiceId(newServices.getServiceId());
        return servicesDTO;
    }

    public ServicesDTO update(ServicesDTO servicesDTO, int id){
        Optional<Services> optionalServices = _servicesRepository.findById(id);
        if (optionalServices.isPresent()){
            Services updateServices = optionalServices.get();
            updateServices.setServiceName(servicesDTO.getServiceName());
            updateServices.setPrice(servicesDTO.getPrice());
            updateServices.setDescription(servicesDTO.getDescription());
            updateServices.setDuration(servicesDTO.getDuration());
            updateServices.setStatus(servicesDTO.getStatus());
            updateServices.setType(servicesDTO.getType());
            updateServices.setCreated_at(LocalDateTime.now());
            updateServices.setUpdated_at(LocalDateTime.now());
            _servicesRepository.save(updateServices);
        }else {
            servicesDTO.setServiceId(-1);
        }
        return servicesDTO;
    }

    public ServicesDTO delete(int id){
        Optional<Services> optionalServices = _servicesRepository.findById(id);
        ServicesDTO servicesDTO = new ServicesDTO();
        if (optionalServices.isPresent()){
            Services services = optionalServices.get();
            servicesDTO = new ServicesDTO(services.getServiceId(),services.getServiceName()
                    ,services.getPrice(),services.getDescription(),services.getDuration(),services.getStatus()
                    ,services.getType(),services.getSkinType(),services.getCreated_at(),services.getUpdated_at());
            _servicesRepository.delete(services);
        }else{
            servicesDTO.setServiceId(-1);
        }
        return servicesDTO;
    }

}
