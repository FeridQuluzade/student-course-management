package student_management_system.demo3.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import student_management_system.demo3.model.ErrorDto;
import student_management_system.demo3.model.exception.StudentNameException;

@ControllerAdvice
public class StudentErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNameException.class)
    public ResponseEntity<Object> handleStudentNameException(StudentNameException studentNameException,
                                                             WebRequest webRequest) {
        return handleExceptionInternal(studentNameException,
                new ErrorDto("student.name.exception",
                        studentNameException.getMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAllExceptions(Exception e,
                                                      WebRequest request) {
        return handleExceptionInternal(e,
                new ErrorDto("unexpected.exception", e.getMessage()),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
