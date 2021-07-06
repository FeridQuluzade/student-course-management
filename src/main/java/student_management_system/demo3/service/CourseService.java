package student_management_system.demo3.service;

import org.springframework.stereotype.Service;
import student_management_system.demo3.dao.CourseMapper;
import student_management_system.demo3.dao.CourseRepository;
import student_management_system.demo3.model.Course;

import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addNewCourse(Course course){
        addNewCourse(null,course);
        return course;
    }

    public Course addNewCourse(UUID courseId,Course course){
        UUID newCourseId= Optional.ofNullable(courseId)
                .orElse(UUID.randomUUID());
        courseRepository.insertCourse(newCourseId,course);

        return course;
    }
}
