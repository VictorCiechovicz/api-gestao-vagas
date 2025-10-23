package com.gestaovagas.modules.company.controllers;


import com.gestaovagas.modules.company.entities.JobEntity;
import com.gestaovagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;


    @PostMapping("/create")
    public ResponseEntity<Object> jobCompany(@RequestBody JobEntity jobEntity) {
        try {
            jobRepository.save(jobEntity);
            return ResponseEntity.ok().body("Job cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
