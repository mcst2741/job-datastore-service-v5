package com.jobstorage.jobdatastoreservicev5.domain;

//If the job does not exist in the datastore this exception is thrown.
public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String jobid){
        super("The job with the ID of " + jobid + " was not found!" );
    }

}