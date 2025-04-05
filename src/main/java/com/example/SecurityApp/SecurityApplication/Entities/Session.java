package com.example.SecurityApp.SecurityApplication.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String refreshToken;
    @CreationTimestamp
    private LocalDateTime lastUsedAt;

    @ManyToOne
    private User user;


}
