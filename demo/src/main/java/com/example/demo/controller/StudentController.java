package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;



    @PostMapping("/createstudent")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.ok().body(studentRepository.save(student));
    }

    @GetMapping("/findallstudents")
    public ResponseEntity<List<Student>> findAllStudents(){
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok().body(students);
    }
}
