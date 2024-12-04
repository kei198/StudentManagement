package raisetech.student.management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.exception.TestException;
import raisetech.student.management.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST　APIとして実行されるControllerです。
 */
@Validated
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
  public List<StudentDetail> getStudentsList(){
    return service.searchStudentsList();
  }

  /**
   * 受講生詳細検索
   * IDに紐づく任意の受講生の詳細情報を取得します。
   * @param id　受講生ID
   * @return　受講生詳細情報
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @Min(1) @Max(999) int id){
    StudentDetail studentDetail = new StudentDetail(service.searchStudent(id),service.getStudentCourseList(id));
    return studentDetail;
  }

  /**
   * 受講生の詳細情報(受講生およびコース情報)を更新します。
   * @param studentDetail　受講生詳細情報　
   * @return　"更新処理が終了しました"
   */
  @Transactional
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList().forEach(sc -> service.updateCourse(sc));
    return ResponseEntity.ok("更新処理が終了しました");
  }

  /**
   * 受講生の詳細情報(受講生およびコース情報)を登録します。
   * @param studentDetail　受講生詳細情報
   * @return　登録した受講生詳細情報
   */
  @Transactional
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.addStudent(studentDetail.getStudent());
    int studentId = studentDetail.getStudent().getId();
    studentDetail.getStudentCourseList().forEach(course -> {
      course.setStudentsId(studentId);
      service.addCourse(course);
    });
    return ResponseEntity.ok(studentDetail);
  }

  @GetMapping("/testerror")
  public void testError() throws TestException {
    throw new TestException("えらー7");
  }

}
