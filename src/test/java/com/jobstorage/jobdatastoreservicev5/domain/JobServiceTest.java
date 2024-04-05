package com.jobstorage.jobdatastoreservicev5.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    void whenJobToCreateAlreadyExistsThenThrows(){
        var jobid = "1";
        var jobToCreate = new Job(jobid,"Title","Description","Company Name","Skill1","Skill2");
        when(jobRepository.existsByJobID(jobid)).thenReturn(true);
        assertThatThrownBy(() -> jobService.addJobToDataStore(jobToCreate))
                .isInstanceOf(JobAlreadyExistsException.class)
                .hasMessage("A job with the ID of " + jobid +" already exists!");
    }

    @Test
    void whenJobToReadDoesNotExistThenThrows(){
        var jobid = "1";
        when(jobRepository.findByJobID(jobid)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> jobService.viewJobDetails(jobid))
                .isInstanceOf(JobNotFoundException.class)
                .hasMessage("The job with the ID of " + jobid + " was not found!");
    }
}