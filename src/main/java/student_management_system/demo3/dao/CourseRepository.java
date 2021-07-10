package student_management_system.demo3.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student_management_system.demo3.dao.mapper.CourseMapper;
import student_management_system.demo3.model.Course;

import java.util.List;
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

    public Optional<Course> selectCourseById(UUID courseId) {

        String sql = "select * from course where course_id=?";

        return Optional.of(jdbcTemplate
                .queryForObject(sql, new Object[]{courseId},
                        courseMapper.mapCourseFromDb()));
    }

    public void updateCourse(UUID courseId, Course course) {
        String sql = "update course set name=?,description=?,department=?,teacher_name=? where course_id=?";
        jdbcTemplate.update(sql,
                course.getName(),
                course.getDescription(),
                course.getDepartment(),
                course.getTeacherName(),
                courseId);
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


    public int deleteCourseById(UUID courseId) {
        String sql = "delete from  course " +
                "where course_id=?";
        return jdbcTemplate.update(sql, courseId);
    }
}
