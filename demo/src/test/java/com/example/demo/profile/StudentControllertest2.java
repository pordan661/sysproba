package com.example.demo.profile;

import com.example.demo.profile.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllertest2 {
    private String azonosito;
  //private StudentRepo repo;


  @Autowired
  private jparepo repo;

    @Autowired
    public StudentControllertest2() {
       
 Student stud = new Student();
    stud.setId(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"));
    stud.setEmail("sanyi@gmail.com");
    stud.setName("Kovacs Sandor");

//repo.save(stud);
azonosito = stud.getId().toString();
    }




    @Autowired
    private MockMvc mockMvc;

  








@Test
public void shouldReturnAllStudents() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/student").accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

    System.out.println(result.getResponse().getContentAsString());
}




@Test
public void shouldReturnStudentById() throws Exception {

    List<Student> ls = repo.findAll();
    Student t = ls.get(0);

    MvcResult result = mockMvc.perform(get("/api/student/"+t.getId().toString()).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

    System.out.println(result.getResponse().getContentAsString());
}

            /*Ez az integracios teszt */
@Test
public void shouldReturnStudencreated() throws Exception {
    repo.deleteAll();
    MvcResult result = mockMvc.perform(post("/api/student/").accept(MediaType.APPLICATION_JSON_VALUE).content("{\"id\":\"a55c919a-7288-466f-9ce6-b62a387962d1\",\"name\":\"Kovecs Sandor\",\"email\":\"sanyi@gmail.com\"}").contentType("application/json"))
            .andExpect(status().isCreated())
           .andReturn();

    System.out.println(result.getResponse().getContentAsString());

List<Student> st = repo.getStudentByName("Kovecs Sandor");
assertTrue(st.size()==1, "aaa");

}


@Test
public void shouldReturnStudenmodified() throws Exception {
Student t = new Student();
t.setId(UUID.fromString("a55c919a-7288-466f-9ce6-b62a387962d1"));

    MvcResult result = mockMvc.perform(put("/api/student/"+t.getId().toString()).accept(MediaType.APPLICATION_JSON_VALUE).content("{\"id\":\"a55c919a-7288-466f-9ce6-b62a387962d1\",\"name\":\"Kovacs Sandor\",\"email\":\"sanyi@gmail.com\"}").contentType("application/json"))
            .andExpect(status().isOk())
           .andReturn();

    System.out.println(result.getResponse().getContentAsString());



}




@Test
public void shouldReturnStudentdeleted() throws Exception {
//repo.deleteAll();

 Student stud = new Student();
    stud.setId(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"));
    stud.setEmail("sanyi@gmail.com");
    stud.setName("Kovacs Sandor");

//repo.save(stud);
//repo.update(stud);
    MvcResult result = mockMvc.perform(delete("/api/student/bbcc4621-d88f-4a94-ae2f-b38072bf5087").accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNoContent())
           .andReturn();

    System.out.println(result.getResponse().getContentAsString());
}




}
