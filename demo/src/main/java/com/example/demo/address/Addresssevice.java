package com.example.demo.address;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.profile.Student;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@Service
@RequestMapping(path = "/api/address", produces = "application/json")
public class Addresssevice {
    




@GetMapping
public ResponseEntity<Address> StudentById(){
   Address c = new Address();
   log.info("addreservice megy");
   c.setId(new UUID(10, 1));
   c.setAddress("kar");
    return new ResponseEntity<>(c, HttpStatus.OK);
}


}
