package com.example.demo.address;
import lombok.Data;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
public class Address {
    

   private UUID id;

  private  String address;


  
}
