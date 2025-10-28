package com.gestaovagas.modules.candidate.repository;

import com.gestaovagas.modules.candidate.entities.ApplyJobCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobCandidateRepositoty extends JpaRepository<ApplyJobCandidateEntity, UUID> {
}
