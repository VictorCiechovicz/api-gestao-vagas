package com.gestaovagas.modules.company.services;

import com.gestaovagas.modules.company.entities.JobEntity;
import com.gestaovagas.modules.company.repository.JobRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;


    public ResponseEntity<Object> createJob(JobEntity jobEntity) {
        try {
            jobRepository.save(jobEntity);
            return ResponseEntity.ok().body("Job cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
