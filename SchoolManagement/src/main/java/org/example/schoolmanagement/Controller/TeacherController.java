package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiResponse;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findAllTeachers());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addTeacher(@Valid@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("teacher added successfully"));
    }

    @PutMapping("/{teacher_id}")
    public ResponseEntity<ApiResponse> updateTeacher(@PathVariable Integer teacher_id, @Valid@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher_id, teacher);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("teacher updated"));
    }

    @DeleteMapping("/{teacher_id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Integer teacher_id){
        teacherService.deleteTeacher(teacher_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("teacher deleted"));
    }


    @GetMapping("/{teacher_id}")
    public ResponseEntity<Teacher> getTeacherInformation(@PathVariable Integer teacher_id){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.teacherInformation(teacher_id));
    }

    @PutMapping("/{teacher_id}/course/{course_id}")
    public ResponseEntity<ApiResponse> assignCourseToTeacher(@PathVariable Integer course_id,@PathVariable Integer teacher_id){
        teacherService.assignCourseToTeacher(course_id, teacher_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assigned successfully"));
    }
}
