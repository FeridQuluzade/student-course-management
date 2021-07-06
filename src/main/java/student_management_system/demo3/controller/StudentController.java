package student_management_system.demo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") @Valid UUID studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping(path = "{studentId}/courses")
    public List<StudentCourse> getAllCoursesForStudent(
            @PathVariable("studentId") UUID studentId) {
        return studentService.getAllCoursesForStudent(studentId);
    }

    @PostMapping(path = "")
    public Student addNewStudent(@RequestBody @Valid Student student) {
        studentService.addNewStudent(student);
        return student;
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
    public void deleteStudent(@PathVariable("studentId") UUID studentId) {
        studentService.deleteStudent(studentId);
    }

}
