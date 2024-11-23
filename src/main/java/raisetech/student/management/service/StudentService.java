package raisetech.student.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.controller.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索・更新処理を行います。
 */
@Service
public class StudentService {
  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository,StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生一覧検索です。
   * 削除処理は除きます。
   * @return　受講生一覧(全件)
   */
  public List<StudentDetail> searchStudentsList(){
    List<Student> studentsList = repository.searchStudents();
    List<StudentCourse> studentsCourses = repository.searchStudentsCourses();
    return converter.convertStudentDetails(studentsList,studentsCourses) ;
  }

  /**
   * 受講生のコース情報を取得します。
   * @param studentId　受講生ID
   * @return　受講生IDに紐づくコース情報(リスト)
   */
  public List<StudentCourse> getStudentsCourse(int studentId){
    return repository.getStudentsCourses(studentId);
  }

  /**
   * IDで受講生を検索します。
   * @param id　受講生ID
   * @return　受講生
   */
  public Student searchStudent(int id){
    return repository.searchStudent(id);
  }

  /**
   * 登録された最新の受講生IDを返す。
   * 受講生とコースを同時に登録する際に、コースの外部キーである受講生のIDを取得する際に必要。
   * @return　最新の受講生ID
   */
  public  int searchLatestStudentId(){
    return repository.searchLatestStudentId();
  }

  /**
   * 受講生を追加する。
   * @param addStudent　追加する受講生
   */
  @Transactional
  public void addStudent(Student addStudent){
    repository.addStudent(addStudent);
  }

  /**
   * コース情報を追加する
   * @param studentCourse　コース情報
   * @param studentsId　登録するコースの受講生ID
   */
  @Transactional
  public void addCourse(StudentCourse studentCourse,int studentsId){
    repository.addCourse(studentCourse,studentsId);
  }

  /**
   * 受講生情報を更新する。
   * @param updateStudent　更新する受講生
   */
  @Transactional
  public void updateStudent(Student updateStudent){
    repository.updateStudent(updateStudent);
  }


  /**
   * コース情報を更新する。
   * @param studentCourse　更新するコース
   */
  @Transactional
  public void updateCourse(StudentCourse studentCourse){
    repository.updateCourse(studentCourse);
  }

}
