package com.journal.repositories;

import com.journal.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    @Query("SELECT subject FROM Subject subject  WHERE subject.name = :name")
    Subject findByName(@Param("name") String name);
}
