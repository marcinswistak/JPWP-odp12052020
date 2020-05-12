package com.example.demo.controller;


import com.example.demo.model.Note;
import com.example.demo.model.Student;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.AddNoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/findallnotes")
    public ResponseEntity<List<Note>> findAllNotes(){
        List<Note> notes = noteRepository.findAll();

        return ResponseEntity.ok().body(notes);
    }



    @PostMapping("/createnote")
    public ResponseEntity<Note> createNote(@Valid @RequestBody AddNoteRequest addNoteRequest){
        Student student =  studentRepository.findById(addNoteRequest.getStudentId()).orElseThrow(() -> new IllegalArgumentException());
        Note note = new Note(addNoteRequest.getValue(),addNoteRequest.getDescription(), student);

        return ResponseEntity.ok().body(noteRepository.save(note));
    }

    @GetMapping("/findnotebystudent/{studentId}")
    public ResponseEntity<Note> findByStudentId(@PathVariable("studentId") Long id){

        return ResponseEntity.ok().body(noteRepository.findByStudent_Id(id).orElseThrow(() -> new IllegalArgumentException()));
    }


}
