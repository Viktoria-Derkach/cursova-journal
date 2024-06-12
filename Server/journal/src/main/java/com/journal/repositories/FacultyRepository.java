package com.journal.repositories;

import com.journal.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {

    @Query("Select faculty FROM Faculty faculty WHERE faculty.name = :name")
    Faculty findByName(@Param("name") String name);
}
