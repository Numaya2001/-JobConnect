package com.jobconnect.security;
import com.jobconnect.repo.UserRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository users;
 public CustomUserDetailsService(UserRepository users){this.users=users;}
 public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
  var u=users.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
  return User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole().name()).build();
 }
}
