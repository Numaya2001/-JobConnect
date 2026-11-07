package com.jobconnect.controller;
import com.jobconnect.model.*; import com.jobconnect.repo.UserRepository; import com.jobconnect.security.JwtService;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.*; import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; private final AuthenticationManager auth; private final JwtService jwt;
 public AuthController(UserRepository u,PasswordEncoder e,AuthenticationManager a,JwtService j){users=u;encoder=e;auth=a;jwt=j;}
 public record RegisterRequest(@NotBlank String fullName,@Email @NotBlank String email,@Size(min=8) String password,Role role){}
 public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
 @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r){
  if(users.existsByEmail(r.email()))return ResponseEntity.badRequest().body(Map.of("message","Email already registered"));
  User u=new User();u.setFullName(r.fullName());u.setEmail(r.email().toLowerCase());u.setPassword(encoder.encode(r.password()));
  u.setRole(r.role()==null?Role.JOB_SEEKER:r.role()==Role.ADMIN?Role.JOB_SEEKER:r.role());users.save(u);
  return ResponseEntity.status(201).body(Map.of("message","Account created"));
 }
 @PostMapping("/login") public Map<String,Object> login(@Valid @RequestBody LoginRequest r){
  auth.authenticate(new UsernamePasswordAuthenticationToken(r.email().toLowerCase(),r.password()));
  User u=users.findByEmail(r.email().toLowerCase()).orElseThrow();
  return Map.of("token",jwt.generate(u.getEmail(),u.getRole().name()),"user",Map.of("id",u.getId(),"fullName",u.getFullName(),"email",u.getEmail(),"role",u.getRole()));
 }
}
