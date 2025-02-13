package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int blogId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users users;

    private String title;

    private String content;

    private LocalDateTime created_at;
}
