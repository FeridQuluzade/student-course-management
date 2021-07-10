package student_management_system.demo3.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import student_management_system.demo3.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        String studentIdStr = resultSet.getString("student_id");
        UUID studentId = UUID.fromString(studentIdStr);

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");

        String genderStr = resultSet.getString("gender").toUpperCase();
        Student.Gender gender = Student.Gender.valueOf(genderStr);

        return new Student(studentId, firstName, lastName, email, gender);
    }
}
