package raisetech.student.management.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentsList")
  public String getStudentsList(Model model) {
    List<Student> students = service.searchStudentsList();
    List<StudentCourse> studentsCourses = service.searchStudentsCoursesList();
    model.addAttribute("studentsList", converter.convertStudentDetails(students, studentsCourses));
    return "studentsList";
  }


  @GetMapping("/studentsCoursesList")
  public List<StudentCourse> getStudentsCoursesList() {
    return service.searchStudentsCoursesList();
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourses(Arrays.asList(new StudentCourse()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @GetMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") int id, Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(service.searchStudent(id));
    model.addAttribute("studentDetail",studentDetail);
    return "updateStudent";
  }

  @PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    service.updateStudent(studentDetail.getStudent());
    return "redirect:/studentsList";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    service.addStudent(studentDetail.getStudent());
    service.addCourse(studentDetail.getStudentsCourses().getFirst(),service.searchStudentId());
    return "redirect:/studentsList";
  }

}
