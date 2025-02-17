package com.example.be_swp.Controller;

import com.example.be_swp.Service.ExpertOccupiedTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/occupiedTime")
public class ExpertOccupiedTimesController {

    private final ExpertOccupiedTimeService _expertOccupiedTimeService;

    public ExpertOccupiedTimesController(ExpertOccupiedTimeService expertOccupiedTimeService) {
        _expertOccupiedTimeService = expertOccupiedTimeService;
    }

}
