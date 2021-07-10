package student_management_system.demo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student_management_system.demo3.model.Student;
import student_management_system.demo3.model.StudentCourse;
import student_management_system.demo3.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        System.out.println("lsjgla");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") @Valid UUID studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping(path = "{studentId}/courses")
    public ResponseEntity<List<StudentCourse>> getAllCoursesForStudent(
            @PathVariable("studentId") UUID studentId) {
        return ResponseEntity.ok(studentService.getAllCoursesForStudent(studentId));
    }

    @PostMapping(path = "")
    public ResponseEntity<Student> addNewStudent(@RequestBody @Valid Student student) {
        studentService.addNewStudent(student);
        return ResponseEntity.ok(student);
    }

    @PatchMapping (path = "{studentId}")
    public int updateEmail(@PathVariable("studentId") UUID studentId,
                         @RequestBody String email) {
        return studentService.updateEmail(studentId, email);

    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") UUID studentId,
                              @RequestBody Student student) {
        studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") UUID studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

}
