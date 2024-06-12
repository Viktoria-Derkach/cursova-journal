package com.journal.repositories;

import com.journal.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    @Query("SELECT teacher.password FROM Teacher teacher WHERE teacher.email = :email")
    Optional<String> getPasswordByEmail(@Param("email") String email);

    @Query("SELECT teacher FROM Teacher teacher WHERE teacher.email = :email")
    Teacher getUserByEmail(@Param("email") String email);

    @Query("SELECT teacher FROM Teacher teacher  WHERE teacher.name = :name")
    Teacher findByName(@Param("name") String name);
}
