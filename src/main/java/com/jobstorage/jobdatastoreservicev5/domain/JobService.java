package com.jobstorage.jobdatastoreservicev5.domain;

import org.springframework.stereotype.Service;
//The use cases in the application requirements are implemented here.
@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public Iterable<Job> viewJobList(){
        return jobRepository.findAll();
    }

    public Job viewJobDetails(String jobid){
        return jobRepository.findByJobID(jobid).
                orElseThrow(() -> new JobNotFoundException(jobid));
    }

    public Job addJobToDataStore(Job job){
        if(jobRepository.existsByJobID(job.jobid())){
            throw new JobAlreadyExistsException(job.jobid());
        }
        return jobRepository.save(job);
    }

    public void removeJobFromDataStore(String jobid){
        jobRepository.deleteByJobID(jobid);
    }

    public Job editJobDetails(String jobid, Job job){
        return jobRepository.findByJobID(jobid).map(existingJob -> {
                    var jobToUpdate = new Job(
                            existingJob.jobid(),
                            job.title(),
                            job.companyName(),
                            job.description(),
                            job.skill1(),
                            job.skill2());
                    return jobRepository.save(jobToUpdate);})
                .orElseGet(() -> addJobToDataStore(job)); //if the current jobID doesn't exist, make a new job with the jobID passed in.
    }
}