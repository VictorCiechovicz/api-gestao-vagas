package com.gestaovagas.modules.candidate.services;

import com.gestaovagas.exceptions.ItemFoundException;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import com.gestaovagas.modules.company.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplyJobCandidateService {


    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    public void execute(UUID candidateId, UUID jobId) {
        var candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new ItemFoundException("Candidate not found"));

        var job = jobRepository.findById(jobId).orElseThrow(() -> new ItemFoundException("Job not found"));

    }
}
