package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty (Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty (Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        //Самый странный метод из присутствующих, сначала он проверяет
        //существует ли действительно искомый факультет в базе данных, хорошо есть
        //необходимый метод existById, затем он прописывает в объект id что бы
        //JPA поняла какой нужен и вот его перезаписала.
        //Обусловлено это механикой save - которая сама определяет создать новую
        // или перезаписать старую запись (insert - update).

        if(faculty.getId() == null || !facultyRepository.existsById(id)) {
            return null;
        }
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColor(String searchedColor) {
        return facultyRepository.findByColor(searchedColor);
    }
}
