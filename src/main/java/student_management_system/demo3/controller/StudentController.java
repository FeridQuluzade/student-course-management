package student_management_system.demo3.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student_management_system.demo3.model.Student;
import student_management_system.demo3.model.StudentCourse;
import student_management_system.demo3.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static student_management_system.demo3.model.HttpReaders.HTTP_HEADER;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") @Valid UUID studentId,
                                                  @RequestHeader(HTTP_HEADER) String httpHeader) {

        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("Response-Header-GetStudentById","getStudentByIdTest");
        System.out.println(httpHeader);
        return new ResponseEntity<>(studentService.getStudentById(studentId),httpHeaders,HttpStatus.OK);
    }

    @GetMapping(path = "{studentId}/courses")
    public ResponseEntity<List<StudentCourse>> getAllCoursesForStudent(
            @PathVariable("studentId") UUID studentId) {

        return new ResponseEntity<>(studentService.getAllCoursesForStudent(studentId),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Student> addNewStudent(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }


    @PutMapping(path = "{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable("studentId") UUID studentId,
                                              @RequestBody Student student) {
        studentService.updateStudent(studentId,student);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") UUID studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PatchMapping(path = "{studentId}")
    public ResponseEntity<Integer> updateEmail(@PathVariable("studentId") UUID studentId,
                                               @RequestBody String email) {
        return new ResponseEntity<Integer>( studentService.updateEmail(studentId, email),HttpStatus.ACCEPTED);
    }

}
