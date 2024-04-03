package ait.cohort34.student.dao;

import ait.cohort34.student.Model.Student;
import ait.cohort34.student.dto.StudentDto;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(int id);

    Collection<Student> findAll();

    Student remove(Student student);
}
