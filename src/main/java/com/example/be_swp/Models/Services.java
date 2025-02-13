package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    private String serviceName;

    private double price;

    private String description;

    private int duration;

    private int status;

    private String type;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<ServiceRatings> serviceRatingsList;


}
