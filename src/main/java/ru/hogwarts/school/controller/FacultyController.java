package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    //GET http://localhost:8080/faculty/1
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if(faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    //POST http://localhost:8080/faculty
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping("{id}")
    //PUT http://localhost:8080/faculty/0
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id,@RequestBody Faculty faculty) {
        Faculty editedFaculty = facultyService.editFaculty(id,faculty);
        if (editedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editedFaculty);
    }

    @DeleteMapping("{id}")
    //DELETE http://localhost:8080/faculty/2
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = {"color", "!name"})
    //GET http://localhost:8080/faculty?color=green
    public ResponseEntity<Collection<Faculty>> getFacultyByColor (@RequestParam String color) {
        Collection<Faculty> facultyCollection = facultyService.getFacultyByColor(color);
        return ResponseEntity.ok(facultyCollection);
    }

    @GetMapping(params = {"name","!color"})
    //GET http://localhost:8080/faculty?name=grifindor
    public ResponseEntity<Collection<Faculty>> getFacultyByName (@RequestParam String name) {
        Collection<Faculty> facultyCollection = facultyService.getFacultyByName(name);
        return ResponseEntity.ok(facultyCollection);
    }

    @GetMapping("/students-faculty/{studentsId}")
    //GET http://localhost:8080/student/faculty?studentId
    public ResponseEntity <Faculty> getStudentsFaculty(@PathVariable Long studentsId) {
        Faculty studentsFaculty = facultyService.getStudentsFaculty(studentsId);
        return ResponseEntity.ok(studentsFaculty);
    }


}
