package com.example.demo.profile;

import lombok.Data;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Student {
    
    
  
   @Id
    private UUID id;
    private String name;

    @Email(message = "Must be a well-formed email")
    private String email;

}
