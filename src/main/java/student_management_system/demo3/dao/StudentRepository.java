package student_management_system.demo3.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student_management_system.demo3.model.Student;
import student_management_system.demo3.model.StudentCourse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface StudentRepository {

    public List<Student> selectAllStudents();

    public Student selectStudentById(UUID studentId);

    public int updateEmail(UUID studentId, String email);

    public Student insertStudent(UUID studentId, Student student);

    @SuppressWarnings("ConstantConditions")
    public boolean isEmailTaken(String email);

    public List<StudentCourse> selectAllStudentCourses(UUID studentId);

    public int updateFirstName(UUID studentId, String firstName);

    public int updateLastName(UUID studentId, String lastName);

    @SuppressWarnings("ConstantConditions")
    public boolean selectExistsEmail(UUID studentId, String email);

    public int deleteStudentById(UUID studentId);
}
