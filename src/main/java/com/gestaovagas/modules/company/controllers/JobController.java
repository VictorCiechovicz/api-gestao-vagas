package com.gestaovagas.modules.company.controllers;


import com.gestaovagas.modules.company.entities.JobEntity;
import com.gestaovagas.modules.company.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> jobCompany(@Valid @RequestBody JobEntity jobEntity, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        return jobService.createJob(jobEntity);

    }
}
