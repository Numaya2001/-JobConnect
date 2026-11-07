package com.jobconnect.controller;
import com.jobconnect.model.*;import com.jobconnect.repo.*;import org.springframework.http.HttpStatus;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;import org.springframework.web.server.ResponseStatusException;import java.util.*;
@RestController @RequestMapping("/api/applications")
public class ApplicationController {
 private final ApplicationRepository apps;private final JobRepository jobs;private final UserRepository users;
 public ApplicationController(ApplicationRepository a,JobRepository j,UserRepository u){apps=a;jobs=j;users=u;}
 public record ApplyRequest(String coverLetter){}
 public record StatusRequest(ApplicationStatus status){}
 @PostMapping("/job/{jobId}") @PreAuthorize("hasRole('JOB_SEEKER')") public JobApplication apply(@PathVariable Long jobId,@RequestBody(required=false)ApplyRequest r,Authentication a){
  User u=current(a);Job j=jobs.findById(jobId).orElseThrow();if(apps.existsByJobIdAndApplicantId(jobId,u.getId()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Already applied");
  JobApplication x=new JobApplication();x.setApplicant(u);x.setJob(j);x.setCoverLetter(r==null?null:r.coverLetter());return apps.save(x);
 }
 @GetMapping("/mine") @PreAuthorize("hasRole('JOB_SEEKER')") public List<JobApplication> mine(Authentication a){return apps.findByApplicantId(current(a).getId());}
 @GetMapping("/employer") @PreAuthorize("hasAnyRole('EMPLOYER','ADMIN')") public List<JobApplication> employer(Authentication a){return apps.findByJobEmployerId(current(a).getId());}
 @PutMapping("/{id}/status") @PreAuthorize("hasAnyRole('EMPLOYER','ADMIN')") public JobApplication status(@PathVariable Long id,@RequestBody StatusRequest r,Authentication a){
  JobApplication x=apps.findById(id).orElseThrow();if(!x.getJob().getEmployer().getEmail().equals(a.getName())&&!admin(a))throw new ResponseStatusException(HttpStatus.FORBIDDEN);
  x.setStatus(r.status());return apps.save(x);
 }
 private User current(Authentication a){return users.findByEmail(a.getName()).orElseThrow();}
 private boolean admin(Authentication a){return a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));}
}
