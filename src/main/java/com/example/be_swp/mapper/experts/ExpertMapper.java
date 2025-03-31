package com.example.be_swp.mapper.experts;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.Models.Experts;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpertMapper {

    public List<?> mapToExperts(List<Experts> experts) {
        List<ExpertsDTO> dto = new ArrayList<>();
        experts.forEach(expert -> {
            ExpertsDTO dto1 = new ExpertsDTO();
            // Use the mapped Users relationship
            if (expert.getUsers() != null) { // Check if user exists
                dto1.setDescription(expert.getDescription());
                dto1.setFullName(expert.getUsers().getFullName());
                dto1.setSpecialization(expert.getSpecialization());
                dto1.setImageBase64(expert.getImageBase64());
                dto1.setStatus(expert.getStatus());
                dto1.setExpertId(expert.getExpertId());
                dto1.setUserId(expert.getUsers().getId());
                dto1.setYearOfExperiences(expert.getYearOfExperiences());
                dto.add(dto1);
            }
        });
        if(!dto.isEmpty()) {
            return dto;
        }
        return experts;
    }
}