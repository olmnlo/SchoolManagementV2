package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiException;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

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
}
