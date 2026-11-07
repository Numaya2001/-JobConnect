package com.jobconnect.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="saved_jobs", uniqueConstraints=@UniqueConstraint(columnNames={"job_id","user_id"}))
public class SavedJob {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne @JoinColumn(name="job_id",nullable=false) private Job job;
 @ManyToOne @JoinColumn(name="user_id",nullable=false) private User user;
 private LocalDateTime savedAt=LocalDateTime.now();
 public Long getId(){return id;} public Job getJob(){return job;} public void setJob(Job v){job=v;}
 public User getUser(){return user;} public void setUser(User v){user=v;}
}
