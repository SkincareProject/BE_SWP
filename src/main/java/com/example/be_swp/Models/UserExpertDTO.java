package com.example.be_swp.Models;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.DTOs.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserExpertDTO {
    private UsersDTO usersDTO;
    private ExpertsDTO expertsDTO;
}
