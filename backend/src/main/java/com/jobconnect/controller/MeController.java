package com.jobconnect.controller;
import com.jobconnect.repo.UserRepository;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController @RequestMapping("/api/me")
public class MeController {
 private final UserRepository users;public MeController(UserRepository u){users=u;}
 @GetMapping public Map<String,Object> me(Authentication a){var u=users.findByEmail(a.getName()).orElseThrow();return Map.of("id",u.getId(),"fullName",u.getFullName(),"email",u.getEmail(),"role",u.getRole());}
}
