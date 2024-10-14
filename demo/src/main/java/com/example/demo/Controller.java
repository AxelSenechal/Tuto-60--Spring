package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/users")
    public List<Student> getAllUsers() {

        return studentRepository.findAll();
    }

    @GetMapping("/stages")
    public List<Stage> getAllStages() {
        stageRepository.deleteAll();
        Stage stage = new Stage();
        stage.setName("Stage 1");
        stageRepository.save(stage);

        return stageRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<Student> getUserById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @PostMapping("/users")
    public Student createUser(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/users/{id}")
    public Student updateUser(@PathVariable Long id, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(studentDetails.getName());
            student.setLastname(studentDetails.getLastname());
            return studentRepository.save(student);
        } else {
            // Handle the case where the student is not found
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    @GetMapping("create")
    public Student createStudent( @RequestParam(value = "name", defaultValue = "unknown") String name, @RequestParam(value = "lastname", defaultValue = "unknown") String lastname) throws NotFoundException {
        Student student = new Student(name, lastname);
        return studentRepository.save(student);
    }

    @GetMapping("/createStudent")
    public Student createStudentFromUrl(@RequestParam String name, @RequestParam String lastname) {
        Student student = new Student(name, lastname);
        return studentRepository.save(student);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
