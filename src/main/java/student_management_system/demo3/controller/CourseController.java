package student_management_system.demo3.controller;

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
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Optional<Course> getCourseById(@PathVariable("courseId") @Valid UUID courseId){
        return courseService.getCourseById(courseId);
    }

    @PostMapping("")
    public Course addNewStudent(@RequestBody @Valid Course course){
        courseService.addNewCourse(course);
        return  course;
    }

    @DeleteMapping("{courseId}")
    public void deleteCourseById(@PathVariable("courseId") UUID courseId){
        courseService.deleteStudent(courseId);
    }
}
