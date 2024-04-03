package ait.cohort34.student.service;

import ait.cohort34.student.Model.Student;
import ait.cohort34.student.dao.StudentRepository;
import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ait.cohort34.student.dto.exceptions.StudentNotFoundException;

import java.util.*;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if(studentRepository.findById(studentAddDto.getId()).isPresent()){
            return false;
        }
        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(id, student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Integer id) {
        Student removedStudent = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.remove(removedStudent);
        return new StudentDto(id, removedStudent.getName(), removedStudent.getScores());
    }

    @Override
    public StudentAddDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);
        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
    }

    @Override
    public Iterable<StudentDto> findStudentsByName(String name) {
        List<StudentDto> studentsFoundByName = new ArrayList<>();
        Collection<Student> allStudents = studentRepository.findAll();

        for (Student student : allStudents) {
            if (student.getName().equals(name)) {
                studentsFoundByName.add(new StudentDto(student.getId(), student.getName(), student.getScores()));
            }
        }

        return studentsFoundByName;
    }

    @Override
    public Long getStudentsNamesQuantity(Set<String> names) {
        return (long) names.size();
    }

    @Override
    public Iterable<StudentDto> findStudentsByExamMinScore(String exam, Integer minScore) {
        List<StudentDto> studentsFound = new ArrayList<>();
        Collection<Student> allStudents = studentRepository.findAll();

        for (Student student : allStudents) {
            if (student.getScores().containsKey(exam) && student.getScores().get(exam) >= minScore) {

                studentsFound.add(new StudentDto(student.getId(), student.getName(), student.getScores()));
            }
        }
        return studentsFound;
    }
}

