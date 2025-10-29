package com.gestaovagas.modules.candidate.services;

import com.gestaovagas.exceptions.ItemFoundException;
import com.gestaovagas.modules.candidate.entities.ApplyJobCandidateEntity;
import com.gestaovagas.modules.candidate.repository.ApplyJobCandidateRepositoty;
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
    private final ApplyJobCandidateRepositoty applyJobCandidateRepositoty;


    public ApplyJobCandidateEntity execute(UUID candidateId, UUID jobId) {
        candidateRepository.findById(candidateId).orElseThrow(() -> new ItemFoundException("Candidate not found"));

        jobRepository.findById(jobId).orElseThrow(() -> new ItemFoundException("Job not found"));


        var applyJob = ApplyJobCandidateEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId).build();

        applyJob = applyJobCandidateRepositoty.save(applyJob);
        return applyJob;


    }
}
