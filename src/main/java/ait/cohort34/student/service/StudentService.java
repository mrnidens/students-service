package ait.cohort34.student.service;

import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;

import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentAddDto studentAddDto);

    StudentDto findStudent(Integer id);

    StudentDto removeStudent(Integer id);

    StudentAddDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

    Boolean addScore(Integer id, ScoreDto scoreDto);

    Iterable<StudentDto> findStudentsByName(String name);

    Long getStudentsNamesQuantity(Set<String> names);

    Iterable<StudentDto> findStudentsByExamMinScore(String exam, Integer minScore);
}

