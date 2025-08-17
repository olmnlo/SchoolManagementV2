package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiException;
import org.example.schoolmanagement.DTO.StudentDTO;
import org.example.schoolmanagement.DTO.TeacherDTO;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Model.Student;
import org.example.schoolmanagement.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAllCourse(){
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()){
            throw new ApiException("no courses");
        }
        return courses;
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void updateCourse(Integer course_id, Course course){
        Course oldCourse = courseRepository.findCourseById(course_id);
        if (oldCourse == null){
            throw new ApiException("course not found");
        }

        oldCourse.setName(course.getName());
        oldCourse.setStudents(null);

        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        if (course == null){
            throw new ApiException("course not found");
        }
        courseRepository.delete(course);
    }

    public TeacherDTO findNameTeacherByCourseId(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);

        return new TeacherDTO(course.getTeacher().getName());
    }

    public List<StudentDTO> courseStudent(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : course.getStudents()){
            studentDTOS.add(new StudentDTO(s.getName()));
        }
        return studentDTOS;
    }

}
