package com.jobconnect.controller;
import com.jobconnect.model.*;import com.jobconnect.repo.*;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/saved-jobs") @PreAuthorize("hasRole('JOB_SEEKER')")
public class SavedJobController {
 private final SavedJobRepository saved;private final JobRepository jobs;private final UserRepository users;
 public SavedJobController(SavedJobRepository s,JobRepository j,UserRepository u){saved=s;jobs=j;users=u;}
 @GetMapping public List<SavedJob> all(Authentication a){return saved.findByUserId(user(a).getId());}
 @PostMapping("/{jobId}") public SavedJob add(@PathVariable Long jobId,Authentication a){
  User u=user(a);return saved.findByJobIdAndUserId(jobId,u.getId()).orElseGet(()->{SavedJob s=new SavedJob();s.setUser(u);s.setJob(jobs.findById(jobId).orElseThrow());return saved.save(s);});
 }
 @DeleteMapping("/{jobId}") public void remove(@PathVariable Long jobId,Authentication a){saved.findByJobIdAndUserId(jobId,user(a).getId()).ifPresent(saved::delete);}
 private User user(Authentication a){return users.findByEmail(a.getName()).orElseThrow();}
}
