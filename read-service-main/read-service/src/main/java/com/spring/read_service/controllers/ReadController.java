package com.spring.read_service.controllers;

import com.spring.read_service.CustomAnnotations.Admin;
import com.spring.read_service.CustomAnnotations.User;
import com.spring.read_service.dtos.CustomPageResponse;
import com.spring.read_service.dtos.StudentDTO;
import com.spring.read_service.dtos.SubjectDTO;
import com.spring.read_service.services.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class ReadController {

    @Autowired
    ReadService readService;

    @Admin
    @GetMapping("/name/{name}")
    public List<StudentDTO> getStudentByName(@PathVariable String name) {
        return readService.getStudentByName(name);
    }
    // Get students by age

    @User
    @GetMapping("/age/{age}")
    public List<StudentDTO> getStudentByAge(@PathVariable int age) {
        return readService.getStudentByAge(age);
    }

    // Get all students with pagination
    @GetMapping("/all")
    public ResponseEntity<CustomPageResponse<StudentDTO>> getAllStudents(@RequestParam int page, @RequestParam int size) {
        Page<StudentDTO> studentPage = readService.getAllStudents(page, size);
        CustomPageResponse<StudentDTO> response = new CustomPageResponse<>(
                studentPage.getContent(),
                studentPage.getTotalPages(),
                studentPage.getSize(),
                studentPage.getTotalElements()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Admin
    //find student by id
    @GetMapping("/id/{id}")
    public StudentDTO getStudentById(@PathVariable int id){
        return readService.getStudentById(id);
    }



@User    // Get subjects by name
    @GetMapping("/subjects/{name}")
    public List<SubjectDTO> getSubjectsByName(@PathVariable String name) {
        return readService.findSubjectsByName(name);
    }
}
