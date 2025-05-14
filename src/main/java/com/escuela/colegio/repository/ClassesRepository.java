package com.escuela.colegio.repository;

import com.escuela.colegio.model.ClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<ClassType, Integer> {
    
}
