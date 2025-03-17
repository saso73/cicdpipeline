package com.example.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class JobController {

    @GetMapping("/test")
    public ResponseEntity<Map<String,String>> test() {
        return ResponseEntity.ok().body(Map.of("Testing!!!", "Up an Running"));

    }


    //STEP 1
//    private List<Job> jobs = new ArrayList<>();
//
//
//    @GetMapping("/jobs")
//    public List<Job> findAll() {
//        return jobs;
//    }
//
//    @PostMapping("/jobs")
//    public String createJob(@RequestBody Job job) {
//        jobs.add(job);
//        return "Job created";
//    }


    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return "Job created";
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity <Job> getJobById(@PathVariable Long id) {

        //what happens if id doesnt exist
//        Job job = jobService.getJobById(id);
//        return job;

        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

    }



    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@RequestBody Job job,
                                            @PathVariable Long id) {
        boolean updated = jobService.updateJob(job, id);
        if (updated) {
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

    }

}
