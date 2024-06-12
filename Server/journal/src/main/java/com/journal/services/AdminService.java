package com.journal.services;

import com.journal.entities.Admin;
import com.journal.repositories.AdminRepository;
import com.journal.services.interfaces.Authorizable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService implements Authorizable {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public String getUserPasswordByEmail(String email) {
        return adminRepository.getPasswordByEmail(email).orElseGet(() -> "");
    }

    @Override
    public Map<String, Object> getUserByEmail(String email) {
        Admin admin = adminRepository.getUserByEmail(email);
        Map<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        return map;
    }
}
