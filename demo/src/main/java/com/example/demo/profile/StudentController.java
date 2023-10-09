package com.example.demo.profile;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@Service
@NoArgsConstructor
@RequestMapping(path = "/api/student", produces = "application/json")


public class StudentController {
    


@Autowired
private jparepo repo;



@GetMapping("/{id}")
public ResponseEntity<Student> StudentById(@PathVariable String id){
  
System.out.println(id);
   

     List<Student> list = repo.findAll();

     for(Student a: list){
        System.out.println(a.getId().toString());
        if(a.getId().toString().equals(id)){
              return new ResponseEntity<>(a, HttpStatus.OK);
        }
     }
 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
   
   
}


@GetMapping
public ResponseEntity<List<Student>> StudentList(){
Student stud = new Student();
    stud.setId(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"));
    stud.setEmail("sanyi@gmail.com");
    stud.setName("Kovacs Sandor");
List<Student> st = repo.findAll();
if(st.size()==0){
repo.save(stud);
}
    List<Student> c = repo.findAll();

    if(c == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return new ResponseEntity<>(c, HttpStatus.OK);
}
@PostMapping(consumes = "application/json")
@ResponseStatus(HttpStatus.CREATED)
public Student AddStudent(@RequestBody Student student){
log.info("create "+student.getId());
return repo.save(student);
}


@DeleteMapping("/{id}")
@ResponseStatus(code=HttpStatus.NO_CONTENT)
public void DeleteStudent(@PathVariable String id){


 List<Student> list = repo.findAll();



    log.info("delete "+id);

 for(Student a: list){
    
        if(a.getId().toString().equals(id)){
             repo.deleteById(a.getId());
        }
     }




}



@PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student){
    if(student==null||student.getId()==null||id==null){
        return ResponseEntity.badRequest().build();
    }

    if(!Objects.equals(id, student.getId().toString())){
        return ResponseEntity.badRequest().build();
    }


    return ResponseEntity.ok(repo.update(student));
}




}
