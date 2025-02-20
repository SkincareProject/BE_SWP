package com.example.be_swp.DTOs.Appointments;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.DTOs.ServicesDTO;
import com.example.be_swp.DTOs.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUserDTO {
    private UsersDTO usersDTO;
    private AppointmentsDTO appointmentsDTO;
    private ExpertsDTO expertsDTO;
    private ServicesDTO servicesDTO;
}
