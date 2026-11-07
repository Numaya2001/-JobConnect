package com.jobconnect.security;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
 private final JwtService jwt; private final CustomUserDetailsService users;
 public JwtAuthFilter(JwtService jwt,CustomUserDetailsService users){this.jwt=jwt;this.users=users;}
 @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization");
  if(h!=null&&h.startsWith("Bearer ")&&SecurityContextHolder.getContext().getAuthentication()==null){
   String token=h.substring(7);
   try{String email=jwt.extractEmail(token); UserDetails d=users.loadUserByUsername(email);
    if(jwt.valid(token)){var a=new UsernamePasswordAuthenticationToken(d,null,d.getAuthorities());a.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));SecurityContextHolder.getContext().setAuthentication(a);}
   }catch(Exception ignored){}
  }
  chain.doFilter(req,res);
 }
}
