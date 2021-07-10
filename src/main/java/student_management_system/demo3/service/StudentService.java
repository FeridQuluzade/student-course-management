package student_management_system.demo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import student_management_system.demo3.EmailValidator;
import student_management_system.demo3.dao.StudentRepository;
import student_management_system.demo3.exception.ApiRequestException;
import student_management_system.demo3.model.Student;
import student_management_system.demo3.model.StudentCourse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EmailValidator emailValidator;


    public StudentService(StudentRepository studentRepository,
                          EmailValidator emailValidator) {
        this.studentRepository = studentRepository;
        this.emailValidator = emailValidator;
    }

    public List<Student> getAllStudents() {
        System.out.println("adsdjhgja");
        return studentRepository.selectAllStudents();
    }

    public Student getStudentById(UUID studentId) {
        return studentRepository.selectStudentById(studentId);
    }

    public int updateEmail(UUID studentId, String email) {
        return studentRepository.updateEmail(studentId, email);
    }

    public Student addNewStudent(Student student) {
        addNewStudent(null, student);
        return student;
    }

    public Student addNewStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId)
                .orElse(UUID.randomUUID());

//        if (!emailValidator.test(student.getEmail())) {
//            throw new ApiRequestException(student.getEmail() + "is not valid");
//        }

//        if (studentRepository.isEmailTaken(student.getEmail())) {
//            throw new ApiRequestException(student.getEmail() + "is taken");
//        }
        studentRepository.insertStudent(newStudentId, student);
        return student;
    }


    public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        return studentRepository.selectAllStudentCourses(studentId);
    }

    public void updateStudent(UUID id, Student student) {
        Optional.ofNullable(student.getEmail())
                .ifPresent(email -> {
                    boolean taken = studentRepository.selectExistsEmail(id, email);
                    if (!taken) {
                        studentRepository.updateEmail(id, email);
                    } else {
                        throw new IllegalStateException("Email already in use:" + student.getEmail());
                    }
                });

        Optional.ofNullable(student.getFirstName())
                .filter(fistName -> !StringUtils.isEmpty(fistName))
                .map(StringUtils::capitalize)
                .ifPresent(firstName -> studentRepository.updateFirstName(id, firstName));

        Optional.ofNullable(student.getLastName())
                .filter(lastName -> !StringUtils.isEmpty(lastName))
                .map(StringUtils::capitalize)
                .ifPresent(lastName -> studentRepository.updateLastName(id, lastName));
    }

    public void deleteStudent(UUID studentId) {
        studentRepository.deleteStudentById(studentId);
    }

}
