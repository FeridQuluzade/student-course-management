package student_management_system.demo3.controller;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable("courseId") @Valid UUID courseId) {
        return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Course> addNewCourse(@RequestBody @Valid Course course) {
        return new ResponseEntity<>(courseService.addNewCourse(course), HttpStatus.CREATED);
    }

    @PutMapping("{courseId}")
    public ResponseEntity<Void> updateCourse(@PathVariable("courseId") UUID courseId,
                                             @RequestBody Course course) {
        courseService.updateCourse(courseId, course);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable("courseId") UUID courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
