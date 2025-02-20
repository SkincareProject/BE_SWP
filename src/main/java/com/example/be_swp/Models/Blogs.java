package com.example.be_swp.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name="blogs")
public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int blogId;

//    @ManyToOne
    @JoinColumn(name = "author_id"
    @Column(name="author_id")
    private Long authorId;

    @Column(name="title")
    private String title;


    @Column(name = "content")
    private String content;


    @Column(name="created_at")
    private LocalDateTime createdAat;
}
