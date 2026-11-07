package com.jobconnect.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="jobs")
public class Job {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false) private String companyName;
 private String location;
 private String employmentType;
 private String experienceLevel;
 @Column(length=5000) private String description;
 @Column(length=2000) private String requirements;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="employer_id", nullable=false) private User employer;
 private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getCompanyName(){return companyName;} public void setCompanyName(String v){companyName=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getEmploymentType(){return employmentType;} public void setEmploymentType(String v){employmentType=v;}
 public String getExperienceLevel(){return experienceLevel;} public void setExperienceLevel(String v){experienceLevel=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public String getRequirements(){return requirements;} public void setRequirements(String v){requirements=v;}
 public User getEmployer(){return employer;} public void setEmployer(User v){employer=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
