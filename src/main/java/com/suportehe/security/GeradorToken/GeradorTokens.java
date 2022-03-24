
  package com.suportehe.security.GeradorToken;
  
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  
  public class GeradorTokens {
  
  public static void main(String[] args) { BCryptPasswordEncoder encoder = new
  BCryptPasswordEncoder(); System.out.println(encoder.encode("123")); }
  
  }
 
