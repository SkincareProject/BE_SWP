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

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList;

    public Services(String serviceName, double price, String description, int duration, int status, String type, LocalDateTime created_at, LocalDateTime updated_at) {
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
