package com.example.Health.Assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disease")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diseaseName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "disease_id")
    private List<Question> questions = new ArrayList<>();
}
