package com.journal.repositories;

import com.journal.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query("SELECT admin.password FROM Admin admin WHERE admin.email = :email")
    Optional<String> getPasswordByEmail(@Param("email") String email);

    @Query("SELECT admin FROM Admin admin WHERE admin.email = :email")
    Admin getUserByEmail(@Param("email") String email);
}
