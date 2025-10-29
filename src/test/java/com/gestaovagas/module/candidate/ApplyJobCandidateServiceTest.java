package com.gestaovagas.module.candidate;

import com.gestaovagas.exceptions.ItemFoundException;
import com.gestaovagas.modules.candidate.entities.ApplyJobCandidateEntity;
import com.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.gestaovagas.modules.candidate.repository.ApplyJobCandidateRepositoty;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import com.gestaovagas.modules.candidate.services.ApplyJobCandidateService;
import com.gestaovagas.modules.company.entities.JobEntity;
import com.gestaovagas.modules.company.repository.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateServiceTest {

    @InjectMocks
    private ApplyJobCandidateService applyJobCandidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobCandidateRepositoty applyJobCandidateRepositoty;

    @Test
    @DisplayName("Candidate and Job not found")
    public void whenApplyJobCandidate_thenNotFound() {
        try {
            applyJobCandidateService.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ItemFoundException.class);
        }
    }

    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateService.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ItemFoundException.class);
        }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobCandidateEntity.builder().candidateId(idCandidate)
                .jobId(idJob).build();

        var applyJobCreated = ApplyJobCandidateEntity.builder().id(UUID.randomUUID()).build();
        
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobCandidateRepositoty.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateService.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

        try {
            applyJobCandidateService.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ItemFoundException.class);
        }
    }
}
