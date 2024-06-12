package com.journal.controlers;

import com.journal.services.StudentService;
import com.journal.services.interfaces.Transferable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final Transferable service;

    @Autowired
    UpdateController(StudentService studentService) {
        this.service = studentService;
    }

    @PostMapping("/transfer/student")
    public ResponseEntity<?> transferStudents(@RequestBody List<String> id) {
        service.transfer(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
