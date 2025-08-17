package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiException;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Model.Student;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.CourseRepository;
import org.example.schoolmanagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public List<Student> findAllStudent(){
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()){
            throw new ApiException("no students");
        }
        return students;
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public void updateStudent(Integer student_id, Student student){
        Student oldStudent = studentRepository.findStudentById(student_id);
        if (oldStudent == null){
            throw new ApiException("student not found");
        }

        oldStudent.setAge(student.getAge());
        oldStudent.setName(student.getName());
        oldStudent.setMajor(student.getMajor());
        oldStudent.setCourses(null);

        studentRepository.save(oldStudent);
    }

    public void deleteStudent(Integer student_id){
        Student student = studentRepository.findStudentById(student_id);
        if (student == null){
            throw new ApiException("student not found");
        }
        studentRepository.delete(student);
    }


    public void changeMajorStudent(Integer student_id, String major){
        Student oldStudent = studentRepository.findStudentById(student_id);
        if (oldStudent == null){
            throw new ApiException("student not found");
        }
        oldStudent.setMajor(major);
        for(Course c : oldStudent.getCourses()){
            Course course = courseRepository.findCourseById(c.getId());
            course.getStudents().remove(oldStudent);
            courseRepository.save(c);
            }
        studentRepository.save(oldStudent);
    }

    public void assignCourseToStudent(Integer course_id, Integer student_id){
        Student student = studentRepository.findStudentById(student_id);
        Course course = courseRepository.findCourseById(course_id);
        if (student == null || course == null){
            throw new ApiException("cannot assign");
        }
        course.getStudents().add(student);
        courseRepository.save(course);
        student.getCourses().add(course);
        studentRepository.save(student);
    }
}
