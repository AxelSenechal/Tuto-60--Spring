package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

      List<Stage> findAll();

    Optional<Stage> findById(Long id);

    Stage save(Stage student);

    void deleteById(Long id);
}