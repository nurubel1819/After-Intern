package com.example.work_today.entity;

import com.example.work_today.dto.QuestionsDto;
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

    private String disease;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "disease_id") // this will add fk in question table
    private List<Question> questions = new ArrayList<>();
}

