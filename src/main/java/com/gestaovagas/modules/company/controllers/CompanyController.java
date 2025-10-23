package com.gestaovagas.modules.company.controllers;


import com.gestaovagas.modules.company.entities.CompanyEntity;
import com.gestaovagas.modules.company.repository.CompanyRepository;
import com.gestaovagas.modules.exception.ItemFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                    .ifPresent(company -> {
                        throw new ItemFoundException("Compania ja existe!");
                    });
            companyRepository.save(companyEntity);

            return ResponseEntity.ok().body("Compania cadastrada com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
