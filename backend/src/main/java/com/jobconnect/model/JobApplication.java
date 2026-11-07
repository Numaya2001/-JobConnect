package com.jobconnect.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="applications", uniqueConstraints=@UniqueConstraint(columnNames={"job_id","applicant_id"}))
public class JobApplication {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="job_id",nullable=false) private Job job;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="applicant_id",nullable=false) private User applicant;
 @Enumerated(EnumType.STRING) private ApplicationStatus status=ApplicationStatus.PENDING;
 private String coverLetter;
 private LocalDateTime appliedAt=LocalDateTime.now();
 public Long getId(){return id;} public Job getJob(){return job;} public void setJob(Job v){job=v;}
 public User getApplicant(){return applicant;} public void setApplicant(User v){applicant=v;}
 public ApplicationStatus getStatus(){return status;} public void setStatus(ApplicationStatus v){status=v;}
 public String getCoverLetter(){return coverLetter;} public void setCoverLetter(String v){coverLetter=v;}
 public LocalDateTime getAppliedAt(){return appliedAt;}
}
