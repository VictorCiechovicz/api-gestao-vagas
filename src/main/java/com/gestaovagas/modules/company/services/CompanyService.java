package com.gestaovagas.modules.company.services;

import com.gestaovagas.exceptions.ItemFoundException;
import com.gestaovagas.modules.company.entities.CompanyEntity;
import com.gestaovagas.modules.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<Object> createCompany(CompanyEntity companyEntity) {
        try {
            companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                    .ifPresent(company -> {
                        throw new ItemFoundException("Compania ja existe!");
                    });

            //Encodando a senha antes de salvar no banco
            var passwordEncod = passwordEncoder.encode(companyEntity.getPassword());
            companyEntity.setPassword(passwordEncod);

            companyRepository.save(companyEntity);

            return ResponseEntity.ok().body("Compania cadastrada com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
