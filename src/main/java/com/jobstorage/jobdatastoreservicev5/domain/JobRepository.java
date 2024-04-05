package com.jobstorage.jobdatastoreservicev5.domain;

import java.util.Optional;

//Abstraction for accessing job data.
public interface JobRepository {
    Iterable<Job> findAll();
    Optional<Job> findByJobID(String jobid);
    boolean existsByJobID(String jobid);
    Job save (Job job);
    void deleteByJobID(String jobid);
}