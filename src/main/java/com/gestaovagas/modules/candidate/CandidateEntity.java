package com.gestaovagas.modules.candidate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@Table(name = "vagas")
@Data
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Pattern(regexp = "\\S+", message = "O campo [username] não pode conter espaços")
    private String username;

    @Length(min = 5, max = 100)
    private String password;

    @Email(message = "O campo deve conter um e-mail válido")
    private String email;
    private String description;
    private String curriculum;


}
