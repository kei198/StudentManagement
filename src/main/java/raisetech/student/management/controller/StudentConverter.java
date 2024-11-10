package raisetech.student.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentCourse> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();

    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourses = studentsCourses.stream()
          .filter(studentCourse -> student.getId().equals(studentCourse.getStudentsId()))
          .collect(Collectors.toList());

      studentDetail.setStudentsCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }



}
