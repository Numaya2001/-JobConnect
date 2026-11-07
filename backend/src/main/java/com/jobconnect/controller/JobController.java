package com.jobconnect.controller;
import com.jobconnect.model.*;import com.jobconnect.repo.*;import org.springframework.security.core.Authentication;import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/jobs")
public class JobController {
 private final JobRepository jobs;private final UserRepository users;
 public JobController(JobRepository j,UserRepository u){jobs=j;users=u;}
 public record JobRequest(String title,String companyName,String location,String employmentType,String experienceLevel,String description,String requirements){}
 @GetMapping public List<Job> all(@RequestParam(required=false)String q){return q==null||q.isBlank()?jobs.findAll():jobs.findByTitleContainingIgnoreCaseOrCompanyNameContainingIgnoreCaseOrLocationContainingIgnoreCase(q,q,q);}
 @GetMapping("/{id}") public Job one(@PathVariable Long id){return jobs.findById(id).orElseThrow();}
 @PostMapping @PreAuthorize("hasAnyRole('EMPLOYER','ADMIN')") public Job create(@RequestBody JobRequest r,Authentication a){
  User u=users.findByEmail(a.getName()).orElseThrow();Job j=new Job();apply(j,r);j.setEmployer(u);return jobs.save(j);
 }
 @PutMapping("/{id}") @PreAuthorize("hasAnyRole('EMPLOYER','ADMIN')") public Job update(@PathVariable Long id,@RequestBody JobRequest r,Authentication a){
  Job j=jobs.findById(id).orElseThrow();if(!j.getEmployer().getEmail().equals(a.getName())&&!isAdmin(a))throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.FORBIDDEN);
  apply(j,r);return jobs.save(j);
 }
 @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('EMPLOYER','ADMIN')") public void delete(@PathVariable Long id,Authentication a){
  Job j=jobs.findById(id).orElseThrow();if(!j.getEmployer().getEmail().equals(a.getName())&&!isAdmin(a))throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.FORBIDDEN);jobs.delete(j);
 }
 @GetMapping("/mine") @PreAuthorize("hasAnyRole('EMPLOYER','ADMIN')") public List<Job> mine(Authentication a){return jobs.findByEmployerId(users.findByEmail(a.getName()).orElseThrow().getId());}
 private void apply(Job j,JobRequest r){j.setTitle(r.title());j.setCompanyName(r.companyName());j.setLocation(r.location());j.setEmploymentType(r.employmentType());j.setExperienceLevel(r.experienceLevel());j.setDescription(r.description());j.setRequirements(r.requirements());}
 private boolean isAdmin(Authentication a){return a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));}
}
