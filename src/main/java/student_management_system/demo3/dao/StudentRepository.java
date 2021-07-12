package student_management_system.demo3.dao;

import student_management_system.demo3.model.Student;
import student_management_system.demo3.model.StudentCourse;

import java.util.List;
import java.util.UUID;


public interface StudentRepository {

     List<Student> selectAllStudents();

     Student selectStudentById(UUID studentId);

     int updateEmail(UUID studentId, String email);

     Student insertStudent(UUID studentId, Student student);

    @SuppressWarnings("ConstantConditions")
     boolean isEmailTaken(String email);

     List<StudentCourse> selectAllStudentCourses(UUID studentId);

     int updateFirstName(UUID studentId, String firstName);

     int updateLastName(UUID studentId, String lastName);

    @SuppressWarnings("ConstantConditions")
     boolean selectExistsEmail(UUID studentId, String email);

     int deleteStudentById(UUID studentId);
}
