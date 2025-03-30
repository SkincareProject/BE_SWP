package com.example.be_swp.DTOs.Appointments;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsDTO {

    private Long appointmentId;

    private String expertsName;

    private String serviceName;

    private Long serviceId;

    private Long expertId;

    private Double total;

    private Long startAt;

    private Long endAt;

    private int status;

    private LocalDate date;

    @Nullable
    private Long expertScheduleId;

    private int duration;


}
