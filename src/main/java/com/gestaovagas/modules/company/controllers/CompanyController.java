package com.gestaovagas.modules.company.controllers;


import com.gestaovagas.modules.company.entities.CompanyEntity;
import com.gestaovagas.modules.company.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/create")
    public void createCompany(@Valid @RequestBody CompanyEntity companyEntity) {
        companyService.createCompany(companyEntity);
    }
}
