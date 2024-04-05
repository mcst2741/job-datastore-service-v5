package com.jobstorage.jobdatastoreservicev5.domain;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Job(
        @NotBlank(message="The job ID must be defined.")
        @Pattern(
                regexp = "^[1-9]", //Job ID's must be numerical values and cannot start with a zero.
                message = "The job ID must be in a valid format."
        )
        String jobid,

        //Nothing may be blank or null.
        @NotBlank(message = "The job title must be defined.")
        String title,

        @NotBlank(message = "The job description must be defined.")
        String description,

        @NotBlank(message = "The company name must be defined.")
        String companyName,

        @NotBlank(message = "Skill must exist.")
        String skill1,

        @NotBlank(message = "Skill must exist.")
        String skill2
) {}
