package student_management_system.demo3.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import student_management_system.demo3.model.Course;

import java.util.UUID;

@Configuration
public class CourseMapper {

    @Bean
    public RowMapper<Course> mapCourseFromDb(){
        return ((resultSet, i) -> {
            String courseIdStr=resultSet.getString("course_id");
            UUID course_id=UUID.fromString(courseIdStr);

            String  name=resultSet.getString("name");
            String description=resultSet.getString("description");
            String department=resultSet.getString("department");
            String  teacherName=resultSet.getString("teacher_name");
            return new Course(
                    course_id,
                    name,
                    description,
                    department,
                    teacherName
            );

        });
    }
}
