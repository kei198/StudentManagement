package raisetech.student.management.controller;

import org.springframework.ui.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service,StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentsList")
  public String getStudentsList(Model model){
    List<Student>students = service.serchStudentsList();
    List<StudentCourse> studentsCourses = service.searchStudentsCoursesList();
    model.addAttribute("studentsList",converter.convertStudentDetails(students,studentsCourses));
    return "studentsList";
  }


  @GetMapping("/studentsCoursesList")
  public List<StudentCourse> getStudentsCoursesList(){
    return service.searchStudentsCoursesList();
  }


}
