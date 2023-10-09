package com.example.demo.profile;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
// import jakarta.persistence.*; // for Spring Boot 3

import javax.transaction.Transactional;
// import jakarta.transaction.Transactional; // for Spring Boot 3

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;


@Repository
public class jparepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT t FROM Student t", Student.class);
        return query.getResultList();
      }
    



      @Transactional
      public Student save(Student student) {
        entityManager.persist(student);
        return student;
      }
    
      public Student findById(UUID id) {
        Student student = (Student) entityManager.find(Student.class, id);
        return student;
      }
    
      @Transactional
      public Student update(Student student) {
        entityManager.merge(student);
        return student;
      }
    
      @Transactional
      public Student deleteById(UUID id) {
        Student student = findById(id);
        if (student != null) {
          entityManager.remove(student);
        }
    
        return student;
      }
    

      public List<Student> getStudentByName(String name) {
        TypedQuery<Student> query = entityManager.createQuery(
            "SELECT t FROM Student t WHERE t.name=:name",
            Student.class);
        return query.setParameter("name", name).getResultList();
      }



      @Transactional
      public int deleteAll() {
        Query query = entityManager.createQuery("DELETE FROM Student");
        return query.executeUpdate();
      }


}
