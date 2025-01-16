package com.spring.read_service.repository;

import com.spring.read_service.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Students,Integer> {
    List<Students> findByName(String name);

    List<Students> findByAge(int age);

    List<Students> findByGender(String gender);
}
