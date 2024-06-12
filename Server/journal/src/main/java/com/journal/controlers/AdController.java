package com.journal.controlers;

import com.journal.entities.Ad;
import com.journal.entities.Student;
import com.journal.entities.Teacher;
import com.journal.factories.ExtractAllServiceFactory;
import com.journal.factories.FilterFactory;
import com.journal.factories.FindByIdFactory;
import com.journal.factories.InsertionServiceFactory;
import com.journal.filters.Filter;
import com.journal.filters.StudentFilter;
import com.journal.services.interfaces.FindableById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private ExtractAllServiceFactory extractAllServiceFactory;
    @Autowired
    private FindByIdFactory findByIdFactory;
    @Autowired
    private InsertionServiceFactory insertionServiceFactory;

    @PostMapping("/add")
    public ResponseEntity<?> addAd(@RequestBody Map<String, String> request) {
        String id = request.remove("id");
        id = id == null ? "" : id;
        FindableById<?> teacherService = findByIdFactory.getService("TeacherService");
        Teacher teacher = id.equals("") ? null : (Teacher) teacherService.find(id);
        LocalDate date = LocalDate.parse(request.remove("date"));
        String title = request.remove("title");
        String text = request.remove("text");
        Filter<Student> studentFilter = (StudentFilter) FilterFactory.getInstance().getEntityFilter("student", request);
        List<Student> students = extractAllServiceFactory.getService("StudentService").getAllEntities(studentFilter);
        Ad ad = new Ad(title, text, teacher, date);
        students.forEach(ad::addStudent);
        insertionServiceFactory.getService("AdService").InsertEntity(ad);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAd() {
        List ads = extractAllServiceFactory.getService("AdService").getAll(null);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/get/teacher")
    public ResponseEntity<?> getTeacherAd(@RequestParam String id) {
        Teacher teacher = (Teacher) findByIdFactory.getService("TeacherService").find(id);
        List<Ad> ads = teacher.getAds();
        List<Map<String, Object>> response = ads.stream().sorted(Comparator.comparing(Ad::getPostDate).reversed()).map(ad -> {
            Map<String, Object> map = new HashMap<>();
            map.put("title", ad.getLabel());
            map.put("from", ad.getPostedBy().getName());
            map.put("postDate", ad.getPostDate());
            map.put("text", ad.getText());
            return map;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/student")
    public ResponseEntity<?> getStudentAd(@RequestParam String id) {
        Student student = (Student) findByIdFactory.getService("StudentService").find(id);
        List<Ad> ads = student.getAds();
        List<Map<String, Object>> response = ads.stream().sorted(Comparator.comparing(Ad::getPostDate).reversed()).map(ad -> {
            Map<String, Object> map = new HashMap<>();
            map.put("title", ad.getLabel());
            map.put("from", (ad.getPostedBy() == null) ? "Деканат" : ad.getPostedBy().getName());
            map.put("postDate", ad.getPostDate());
            map.put("text", ad.getText());
            return map;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
