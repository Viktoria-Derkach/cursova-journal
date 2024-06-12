package com.journal.repositories;

import com.journal.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT student.password FROM Student student WHERE student.email = :email")
    Optional<String> getPasswordByEmail(@Param("email") String email);

    @Query("SELECT student FROM Student student WHERE student.email = :email")
    Student getUserByEmail(@Param("email") String email);
}
