package com.gestaovagas.modules.company.controllers;


import com.gestaovagas.modules.company.entities.CompanyEntity;
import com.gestaovagas.modules.company.entities.JobEntity;
import com.gestaovagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    private CompanyRepository companyRepository;
}
