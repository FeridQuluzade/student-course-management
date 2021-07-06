package student_management_system.demo3.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student_management_system.demo3.model.Course;

import java.util.Locale;
import java.util.UUID;

@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CourseMapper courseMapper;

    public CourseRepository(JdbcTemplate jdbcTemplate, CourseMapper courseMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.courseMapper = courseMapper;
    }

    public Course insertCourse(UUID courseId, Course course) {
        String sql = "insert into course" +
                "(course_id," +
                "name," +
                "description," +
                "department," +
                "teacher_name)" +
                " values(?,?,?,?,?)";
        jdbcTemplate.update(sql,
                courseId,
                course.getName(),
                course.getDescription(),
                course.getDepartment(),
                course.getTeacherName());
        return course;
    }
}
