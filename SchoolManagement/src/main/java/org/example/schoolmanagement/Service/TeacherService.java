package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiException;
import org.example.schoolmanagement.DTO.TeacherDTO;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.CourseRepository;
import org.example.schoolmanagement.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public List<Teacher> findAllTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        if (teachers.isEmpty()){
            throw new ApiException("teachers not found");
        }
        return teachers;
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer teacherId, Teacher teacher){
        Teacher oldTeacher = teacherRepository.findTeacherById(teacherId);
        if (oldTeacher == null){
            throw new ApiException("teacher not found");
        }
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setName(teacher.getName());
        oldTeacher.setSalary(teacher.getSalary());
        oldTeacher.setEmail(teacher.getEmail());

        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer teacherId){
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null){
            throw new ApiException("teacher not found");
        }
        teacherRepository.delete(teacher);
    }


    public Teacher teacherInformation(Integer teacherId){
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null){
            throw new ApiException("teacher not found");
        }
        return teacher;
    }


    public void assignCourseToTeacher(Integer course_id, Integer teacher_id){
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        Course course = courseRepository.findCourseById(course_id);
        if (teacher == null || course == null){
            throw new ApiException("cannot assign");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }
}
