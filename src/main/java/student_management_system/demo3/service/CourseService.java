package student_management_system.demo3.service;

import org.springframework.stereotype.Service;
import student_management_system.demo3.dao.CourseRepository;
import student_management_system.demo3.exception.ApiRequestException;
import student_management_system.demo3.model.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.selectAllCourses();
    }

    public Optional<Course> getCourseById(UUID courseId) {
        return findCourseById(courseId);
    }


    private Optional<Course> findCourseById(UUID courseId){
        return Optional.of(courseRepository
                .selectCourseById(courseId)
                .orElseThrow(()-> new ApiRequestException("Course couldn't be found by following id:" + courseId)));
    }

    public Course addNewCourse(Course course) {
        addNewCourse(null, course);
        return course;
    }

    public Course addNewCourse(UUID courseId, Course course) {
        UUID newCourseId = Optional.ofNullable(courseId)
                .orElse(UUID.randomUUID());
        courseRepository.insertCourse(newCourseId, course);

        return course;
    }

    public void deleteCourse(UUID courseId) {
        courseRepository.deleteCourseById(courseId);
    }

    public void updateCourse(UUID courseId,Course course){
        Course course1=findCourseById(courseId).get();
        Course updateUser=new Course(
                course1.getCourseİd(),
                course.getName(),
                course.getDescription(),
                course.getDepartment(),
                course.getTeacherName()
        );
        courseRepository.updateCourse(courseId,updateUser);
    }

}
