package com.journal.repositories;

import com.journal.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, String> {

    @Query("SELECT direction FROM Direction direction  WHERE direction.name = :name")
    Direction findByName(@Param("name") String name);
}
