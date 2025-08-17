package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiResponse;
import org.example.schoolmanagement.Model.Student;
import org.example.schoolmanagement.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAllStudent());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addStudent(@Valid@RequestBody Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/{student_id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable Integer student_id, @Valid@RequestBody Student student){
        studentService.updateStudent(student_id, student);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/{student_id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer student_id){
        studentService.deleteStudent(student_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/{student_id}/major/{major}")
    public ResponseEntity<ApiResponse> updateStudentMajor(@PathVariable Integer student_id,@PathVariable String major){
        studentService.changeMajorStudent(student_id, major);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student major updated successfully"));
    }

    @PutMapping("/{student_id}/course/{course_id}")
    public ResponseEntity<ApiResponse> assignCourseToTeacher(@PathVariable Integer course_id,@PathVariable Integer student_id){
        studentService.assignCourseToStudent(course_id, student_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assigned successfully"));
    }
}
