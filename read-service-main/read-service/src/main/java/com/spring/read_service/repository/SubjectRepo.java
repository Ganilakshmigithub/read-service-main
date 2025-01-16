package com.spring.read_service.repository;

import com.spring.read_service.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.name =:name")
    List<Subject> findSubByName(String name);
}
