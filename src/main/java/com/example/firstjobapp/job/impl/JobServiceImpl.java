package com.example.firstjobapp.job.impl;

import com.example.firstjobapp.job.Job;
import com.example.firstjobapp.job.JobService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class JobServiceImpl implements JobService {

    private final List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;  //job id

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {

        job.setId(nextId++); // job id


        jobs.add(job);

    }

    @Override
    public Job getJobById(Long id) {

        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                jobs.remove(job);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJob(Job updatedJob, Long id) {
        for (Job job2 : jobs) {
            if (job2.getId().equals(id)) {

                job2.setTitle(updatedJob.getTitle());
                job2.setDescription(updatedJob.getDescription());
                job2.setLocation(updatedJob.getLocation());
                job2.setMaxSalary(updatedJob.getMaxSalary());
                job2.setMinSalary(updatedJob.getMinSalary());

                //jobs.set(jobs.indexOf(job), job);  // not working not sure why
                return true;
            }
        }
        return false;
    }
}
