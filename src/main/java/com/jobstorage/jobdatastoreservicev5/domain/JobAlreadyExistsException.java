package com.jobstorage.jobdatastoreservicev5.domain;

//If a job already exists in the data store this exception is thrown.
public class JobAlreadyExistsException extends RuntimeException{
    public JobAlreadyExistsException(String jobid){
        super("A job with the ID of " + jobid+ " already exists!");
    }
}
