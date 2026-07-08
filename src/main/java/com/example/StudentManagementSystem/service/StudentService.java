package com.example.StudentManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Repository.StudentRepository;
import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.exception.ResourceNotFoundException;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Save Student
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Get Student By Id
    public Student getStudentById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));
    }

    // Update Student
    public Student updateStudent(Long id, Student student) {

        Student existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        existing.setName(student.getName());
        existing.setemail(student.getemail());
        existing.setCourse(student.getCourse());
        existing.setAge(student.getAge());

        return repository.save(existing);
    
    }

    // Delete Student
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}