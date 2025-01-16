package com.spring.read_service.services;

import com.spring.read_service.dtos.StudentDTO;
import com.spring.read_service.dtos.SubjectDTO;
import com.spring.read_service.entities.Students;
import com.spring.read_service.entities.Subject;
import com.spring.read_service.repository.StudentRepo;
import com.spring.read_service.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    SubjectRepo subjectRepo;

    private StudentDTO convertToDTO(Students student) {
        List<SubjectDTO> subjectDTOs = student.getSubjects().stream()
                .map(subject -> new SubjectDTO(subject.getSubId(), subject.getName(), subject.getMarks()))
                .collect(Collectors.toList());
        return new StudentDTO(student.getId(),student.getName(), student.getAge(), student.getGender(),
                student.getDob(), student.getCourse(), student.getCourseStartYear(), student.getCourseEndYear(), subjectDTOs);
    }

    //get all students by name
    public List<StudentDTO> getStudentByName(String name) {
        List<Students> students = studentRepo.findByName(name);
       return students.stream().map(student -> convertToDTO(student)).collect(Collectors.toList());
    }
    // Get students by age
    public List<StudentDTO> getStudentByAge(int age) {
        List<Students> students = studentRepo.findByAge(age);
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //find by id
    public StudentDTO getStudentById(int id){
        Students s=studentRepo.findById(id).orElseThrow();
        return convertToDTO(s);

    }
    // Get all students with pagination
    public Page<StudentDTO> getAllStudents(int page, int size) {
        Page<Students> studentsPage = studentRepo.findAll(PageRequest.of(page, size));
        return studentsPage.map(this::convertToDTO);
    }

    //get subjects by their name
    public List<SubjectDTO> findSubjectsByName(String name) {
        List<Subject> subjects = subjectRepo.findSubByName(name);
        return subjects.stream().map(subject -> new SubjectDTO(subject.getSubId(), subject.getName(), subject.getMarks()))
                .collect(Collectors.toList());
    }

}
