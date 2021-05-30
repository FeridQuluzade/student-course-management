package student_management_system.demo3.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import student_management_system.demo3.EmailValidator;
import student_management_system.demo3.exception.ApiRequestException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService,
                          EmailValidator emailValidator) {
        this.studentDataAccessService = studentDataAccessService;
        this.emailValidator = emailValidator;
    }

    List<Student> getAllStudents() {
        return studentDataAccessService.selectAllStudents();
    }

    void addNewStudent(Student student) {
        addNewStudent(null, student);
    }

    void addNewStudent(UUID studentId, Student student) {
        UUID newSTudentId = Optional.ofNullable(studentId)
                .orElse(UUID.randomUUID());

        if (!emailValidator.test(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + "is not valid");
        }

        if (studentDataAccessService.isEmailTaken(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + "is taken");
        }
        studentDataAccessService.insertStudent(newSTudentId, student);
    }

    List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        return studentDataAccessService.selectAllStudentCourses(studentId);
    }

    public void updateStudent(UUID id, Student student) {
        Optional.ofNullable(student.getEmail())
                .ifPresent(email -> {
                    boolean taken = studentDataAccessService.selectExistsEmail(id, email);
                    if (!taken) {
                        studentDataAccessService.updateEmail(id, email);
                    } else {
                        throw new IllegalStateException("Email already in use:" + student.getEmail());
                    }
                });

        Optional.ofNullable(student.getFirstName())
                .filter(fistName -> !StringUtils.isEmpty(fistName))
                .map(StringUtils::capitalize)
                .ifPresent(firstName -> studentDataAccessService.updateFirstName(id, firstName));

        Optional.ofNullable(student.getLastName())
                .filter(lastName -> !StringUtils.isEmpty(lastName))
                .map(StringUtils::capitalize)
                .ifPresent(lastName -> studentDataAccessService.updateLastName(id, lastName));
    }

    void deleteStudent(UUID studentId) {
        studentDataAccessService.deleteStudentById(studentId);
    }

}
