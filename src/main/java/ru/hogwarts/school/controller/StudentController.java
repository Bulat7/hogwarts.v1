package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    //GET http://localhost:8080/student/1
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    //POST http://localhost:8080/student
    public Student createStudent (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("{id}")
    //PUT http://localhost:8080/student
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable Long id) {
        Student editedStudent = studentService.editStudent(student, id);
        if(editedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editedStudent);
    }

    @DeleteMapping("{id}")
    //DELETE http://localhost:8080/student/1
    public ResponseEntity<Void> deleteStudent (@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    //GET http://localhost:8080/student?age=18
    public ResponseEntity<Collection<Student>> getStudentsByAge(@RequestParam String age) {
        Collection<Student> studentCollection = studentService.getStudentsByAge(age);
        return ResponseEntity.ok(studentCollection);
    }

}
