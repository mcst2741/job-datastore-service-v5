package com.jobstorage.jobdatastoreservicev5.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JobValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreCorrectThenValidationSucceeds(){
        var job = new Job("1","Senior Developer","Java Developer","Oracle","Java","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenJobidIsNotDefinedThenValidationFails(){
        var job = new Job("","Senior Developer","Java Developer","Oracle","Java","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(2); //number of violations is exactly two
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("The job ID must be defined.")
                .contains("The job ID must be in a valid format.");
    }

    @Test
    void whenJobidIsDefinedButIsIncorrectThenValidationFails(){
        var job = new Job("0","Senior Developer","Java Developer","Oracle","Java","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The job ID must be in a valid format.");
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails(){
        var job = new Job("1","","Java Developer","Oracle","Java","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The job title must be defined.");
    }

    @Test
    void whenDescriptionIsNotDefinedThenValidationFails(){
        var job = new Job("1","Senior Developer","","Oracle","Java","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The job description must be defined.");
    }

    @Test
    void whenCompanyNameIsNotDefinedThenValidationFails(){
        var job = new Job("1","Senior Developer","Java Developer","","Java","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The company name must be defined.");
    }

    @Test
    void whenSkill1IsNotDefinedThenValidationFails(){
        var job = new Job("1","Senior Developer","Java Developer","Oracle","","JavaScript");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Skill must exist.");
    }

    @Test
    void whenSkill2IsNotDefinedThenValidationFails(){
        var job = new Job("1","Senior Developer","Java Developer","Oracle","Java","");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        //System.out.println("Num of violations "+violations.size());
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Skill must exist.");
    }

}
