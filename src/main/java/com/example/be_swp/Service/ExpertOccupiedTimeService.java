package com.example.be_swp.Service;

import com.example.be_swp.Repository.ExpertOccupiedTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpertOccupiedTimeService {

    private final ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;

    public ExpertOccupiedTimeService(ExpertOccupiedTimeRepository expertOccupiedTimeRepository) {
        _expertOccupiedTimeRepository = expertOccupiedTimeRepository;
    }
}
