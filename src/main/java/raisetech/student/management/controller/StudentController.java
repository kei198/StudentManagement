package raisetech.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST　APIとして実行されるControllerです。
 */
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生一覧検索機能
   * 全件検索を行うので、条件指定は行わない。
   * @return　受講生一覧(全件)
   */
  @GetMapping("/studentsList")
  public List<StudentDetail> getStudentsList() {
    return service.searchStudentsList();
  }

  /**
   * 受講生検索
   * IDに紐づく任意の受講生の情報を取得します。
   * @param id　受講生ID
   * @return　受講生
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable int id){
    StudentDetail studentDetail = new StudentDetail(service.searchStudent(id),service.getStudentsCourse(id));
    return studentDetail;
  }

  /**
   *　
   * @param id
   * @param model
   * @return
   */
  @GetMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") int id, Model model) {
    StudentDetail studentDetail = new StudentDetail(service.searchStudent(id),service.getStudentsCourse(id));
    model.addAttribute("studentDetail",studentDetail);
    return "updateStudent";
  }

  /**
   * 受講生の詳細情報を更新します。
   * @param studentDetail　受講生詳細情報　
   * @return　"更新処理が終了しました"
   */
  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentsCourses().forEach(sc -> service.updateCourse(sc));
    return ResponseEntity.ok("更新処理が終了しました");
  }

  /**
   * 受講生を登録します。
   * @param studentDetail　受講生詳細情報
   * @return　登録した受講生詳細情報
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    service.addStudent(studentDetail.getStudent());
    service.addCourse(studentDetail.getStudentsCourses().getFirst(),service.searchLatestStudentId());
    studentDetail.setStudent(service.searchStudent(service.searchLatestStudentId()));
    studentDetail.setStudentsCourses(service.getStudentsCourse(service.searchLatestStudentId()));
    return ResponseEntity.ok(studentDetail);
  }

}
