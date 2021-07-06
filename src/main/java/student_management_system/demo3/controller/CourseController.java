package student_management_system.demo3.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student_management_system.demo3.model.Course;
import student_management_system.demo3.service.CourseService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course addNewStudent(@RequestBody @Valid Course course){
        courseService.addNewCourse(course);
        return  course;
    }
}
