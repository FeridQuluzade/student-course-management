package student_management_system.demo3.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import student_management_system.demo3.model.Student;
import student_management_system.demo3.model.StudentCourse;

import java.util.List;
import java.util.UUID;
@Repository
@Profile("integration")
public class StudentRepositoryIntegration implements StudentRepository{
  private static final Logger logIntegration=LoggerFactory.getLogger(StudentRepositoryIntegration.class);

    public StudentRepositoryIntegration(){
        logIntegration.info("Integration profile starting");
    }
    @Override
    public List<Student> selectAllStudents() {
        return null;

    }

    @Override
    public Student selectStudentById(UUID studentId) {
        return null;
    }

    @Override
    public int updateEmail(UUID studentId, String email) {
        return 0;
    }

    @Override
    public Student insertStudent(UUID studentId, Student student) {
        return null;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return false;
    }

    @Override
    public List<StudentCourse> selectAllStudentCourses(UUID studentId) {
        return null;
    }

    @Override
    public int updateFirstName(UUID studentId, String firstName) {
        return 0;
    }

    @Override
    public int updateLastName(UUID studentId, String lastName) {
        return 0;
    }

    @Override
    public boolean selectExistsEmail(UUID studentId, String email) {
        return false;
    }

    @Override
    public int deleteStudentById(UUID studentId) {
        return 0;
    }
}
