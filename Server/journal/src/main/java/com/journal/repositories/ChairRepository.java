package com.journal.repositories;

import com.journal.entities.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair, String> {

    @Query("SELECT chair FROM Chair chair  WHERE chair.name = :name")
    Chair findByName(@Param("name") String name);
}
