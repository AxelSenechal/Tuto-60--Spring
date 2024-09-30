package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);

    void deleteById(Long id);

    Iterable<Student> findByLastname(String string);
    
}
