package student_management_system.demo3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Course {
    @JsonIgnore
    private final UUID courseİd;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotBlank
    private final String department;
    @NotBlank
    private final String teacherName;

    public Course(UUID courseİd,
                  String name,
                  String department,
                  String description,
                  String teacherName) {
        this.courseİd = courseİd;
        this.name = name;
        this.department = department;
        this.description = description;
        this.teacherName = teacherName;
    }

    public UUID getCourseİd() {
        return courseİd;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseİd=" + courseİd +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department='" + department + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
