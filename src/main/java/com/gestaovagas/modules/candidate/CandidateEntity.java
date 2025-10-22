package com.gestaovagas.modules.candidate;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "vagas")
@Data
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    private String username;
    private String password;
    private String email;
    private String description;
    private String curriculum;


}
