package com.jobstorage.jobdatastoreservicev5.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import com.jobstorage.jobdatastoreservicev5.domain.Job;
import com.jobstorage.jobdatastoreservicev5.domain.JobRepository;
import org.springframework.stereotype.Repository;

//Implements the JobRepository interface and retrieves and saves job objects.
@Repository
public class InMemoryJobRepository implements JobRepository{
    private static final Map<String, Job> jobs = new ConcurrentHashMap<>();

    @Override
    public Iterable<Job> findAll(){
        return jobs.values();
    }

    @Override
    public Optional<Job> findByJobID(String jobid){
        return existsByJobID(jobid) ? Optional.of(jobs.get(jobid)) :
                Optional.empty();
    }

    @Override
    public boolean existsByJobID(String jobid){
        return jobs.get(jobid) != null;
    }

    @Override
    public Job save(Job job){
        jobs.put(job.jobid(), job);
        return job;
    }

    @Override
    public void deleteByJobID(String jobid){
        jobs.remove(jobid);
    }

}