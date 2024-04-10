package com.jobstorage.jobdatastoreservicev5.web;

import com.jobstorage.jobdatastoreservicev5.domain.Job;
import com.jobstorage.jobdatastoreservicev5.domain.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//Handles HTTP requests sent to the /jobs endpoint.
@RestController
@RequestMapping("jobs")
public class JobController {
    
    @Value("${server.port}")
    private String serverPort;


    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping
    public Iterable<Job> get(){
        return jobService.viewJobList();
    }

    @GetMapping("{jobid}")
    public Job getByJobID(@PathVariable String jobid){
        return jobService.viewJobDetails(jobid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201 status
    public Job post(@Valid @RequestBody Job job){
        return jobService.addJobToDataStore(job);
    }

    @DeleteMapping("{jobid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String jobid){
        jobService.removeJobFromDataStore(jobid);
    }

    @PutMapping("{jobid}")
    public Job put(@PathVariable String jobid,@Valid @RequestBody Job job){
        return jobService.editJobDetails(jobid, job);
    }
}