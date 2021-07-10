package student_management_system.demo3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student_management_system.demo3.model.Course;
import student_management_system.demo3.service.CourseService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable("courseId") @Valid UUID courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping("")
    public ResponseEntity<Course> addNewStudent(@RequestBody @Valid Course course){
        courseService.addNewCourse(course);
        return  ResponseEntity.ok(course);
    }

    @PutMapping("{courseId}")
    public ResponseEntity<Void> updateCourse(@PathVariable("courseId") UUID courseId,
                             @RequestBody Course course){
        courseService.updateCourse(courseId,course);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable("courseId") UUID courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }

}
