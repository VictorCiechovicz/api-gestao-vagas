package com.gestaovagas.modules.candidate.usecase;

import com.gestaovagas.modules.candidate.CandidateEntity;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import com.gestaovagas.modules.exception.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseCase {

    @Autowired
    private CandidateRepository candidateRepository;


    public CandidateRepository createCandidate(CandidateEntity candidateEntity) {
        candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {
                    System.out.println(candidateEntity.getUsername());
                    throw new UserFoundException();
                });
        candidateRepository.save(candidateEntity);

        return candidateRepository;
    }

}
