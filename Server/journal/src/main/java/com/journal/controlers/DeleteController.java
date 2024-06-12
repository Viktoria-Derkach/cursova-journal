package com.journal.controlers;

import com.journal.factories.DeleteServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    private DeleteServiceFactory factory;

    @DeleteMapping("/student")
    public ResponseEntity<?> deleteStudent(@RequestParam("id") String id) {
        System.out.println("log student deletion");
        factory.getService("StudentService").delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/teacher")
    public ResponseEntity<?> deleteTeacher(@RequestParam("id") String id) {
        System.out.println("log student deletion");
        factory.getService("TeacherService").delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/subject")
    public ResponseEntity<?> deleteSubject(@RequestParam("id") String id) {
        System.out.println("log student deletion");
        factory.getService("SubjectService").delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
