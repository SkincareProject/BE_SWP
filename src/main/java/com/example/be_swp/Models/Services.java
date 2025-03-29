package com.example.be_swp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String serviceName;

    private double price;

    private String description;

    private int duration;

    private int status;

    private String type;

    private String skinType;

    private String image;


    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceId", cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList;

    @JsonIgnore
    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<ServiceRatings> serviceRatingsList;
  
    public Services(String serviceName, double price, String description, int duration, int status, String type, String skinType, LocalDateTime created_at, LocalDateTime updated_at,String image) {
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.type = type;
        this.skinType = skinType;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.image = image;
    }
}
