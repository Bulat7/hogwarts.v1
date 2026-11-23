package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student, Long id) {
        if(student.getId() == null || !studentRepository.existsById(id)) {
            return null;
        }
        student.setId(id);
        return studentRepository.save(student);

    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(String ageLimit) {
        return studentRepository.findByAge(ageLimit);
    }

    public Collection<Student> getAllStudentInAgeBetween(Long min, Long max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(Long facultyId) {
        return studentRepository.findByFaculty_Id(facultyId);
    }
}
