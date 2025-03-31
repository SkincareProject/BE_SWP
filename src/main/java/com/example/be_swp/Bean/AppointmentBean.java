package com.example.be_swp.Bean;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentBean {
    private LocalDate date;
    private Long startAt;
    private Long endAt;
    private  Long expertScheduleId;

}
