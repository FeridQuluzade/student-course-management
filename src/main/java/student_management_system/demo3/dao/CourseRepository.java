package student_management_system.demo3.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student_management_system.demo3.exception.ApiRequestException;
import student_management_system.demo3.model.Course;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CourseMapper courseMapper;

    public CourseRepository(JdbcTemplate jdbcTemplate, CourseMapper courseMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.courseMapper = courseMapper;
    }

    public List<Course> selectAllCourses() {
        String sql = "select course_id," +
                "name," +
                "description," +
                "department," +
                "teacher_name" +
                " from course";
        return jdbcTemplate.query(sql, courseMapper.mapCourseFromDb());
    }

    public Optional<Course> selectCourseById(UUID courseId ){
        String sql="select * from course where course_id=?";
        return Optional.of(jdbcTemplate.queryForObject(sql, new Object[]{courseId}, courseMapper.mapCourseFromDb()));
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
