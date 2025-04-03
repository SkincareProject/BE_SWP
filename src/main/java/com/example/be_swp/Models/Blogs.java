package com.example.be_swp.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Table(name="blogs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Blogs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;

    @Column(name="author_id")
    private int authorId;

    @Column(name="title")
    private String title;

    @Column(name="content", columnDefinition = "TEXT")
    private String content;

    private String image;

    @CreationTimestamp
    @Column(name="created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Instant updatedAt;


}
