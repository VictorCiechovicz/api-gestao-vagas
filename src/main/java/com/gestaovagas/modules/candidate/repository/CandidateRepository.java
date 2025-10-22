package com.gestaovagas.modules.candidate.repository;

import com.gestaovagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
}
