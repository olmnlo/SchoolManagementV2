package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiResponse;
import org.example.schoolmanagement.DTO.StudentDTO;
import org.example.schoolmanagement.DTO.TeacherDTO;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Model.Student;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAllCourse());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/{course_id}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable Integer course_id, @Valid@RequestBody Course course){
        courseService.updateCourse(course_id, course);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Course updated successfully"));
    }

    @DeleteMapping("/{course_id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Integer course_id){
        courseService.deleteCourse(course_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Course deleted successfully"));
    }

    @GetMapping("/{course_id}/teacher")
    public ResponseEntity<TeacherDTO> getTeacherInformation(@PathVariable Integer course_id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findNameTeacherByCourseId(course_id));
    }


    @GetMapping("/{course_id}/students")
    public ResponseEntity<List<StudentDTO>> updateCourse(@PathVariable Integer course_id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.courseStudent(course_id));
    }
}
