package com.journal.controlers;

import com.journal.factories.AuthorizationServiceFactory;
import com.journal.services.interfaces.Authorizable;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    @Autowired
    private AuthorizationServiceFactory factory;

    @GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(
            @RequestParam("role") String role,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        System.out.println("log login");
        Authorizable service = switch (role) {
            case "dekanat" -> factory.getService("AdminService");
            case "teacher" -> factory.getService("TeacherService");
            default -> factory.getService("StudentService");
        };
        String existedPassword = service.getUserPasswordByEmail(email);
        if (existedPassword.isEmpty())
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        String encryptedPassword = DigestUtils.sha256Hex(password);
        if (!existedPassword.equals(encryptedPassword)) {
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(service.getUserByEmail(email), HttpStatus.OK);
    }
}
