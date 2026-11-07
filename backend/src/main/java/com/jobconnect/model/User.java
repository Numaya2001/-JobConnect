package com.jobconnect.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String email;
 @Column(nullable=false) private String password;
 @Column(nullable=false) private String fullName;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
 private LocalDateTime createdAt = LocalDateTime.now();
 public Long getId(){return id;} public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
 public Role getRole(){return role;} public void setRole(Role v){role=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
