package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 削除フラグのたっていない受講生の全件検索を行います。
   * @return 受講生一覧
   */
  List<Student> searchStudents();

  /**
   * 受講生のコース情報の全件検索を行います。
   * @return　受講生のコース情報(全件)
   */
  List<StudentCourse> searchStudentCourseList();

  /**
   * 受講生IDに紐づく受講生の検索を行います。
   * @param id　受講生ID
   * @return　受講生IDに紐づく受講生
   */
  Student searchStudent(int id);

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   * @param studentId　受講生ID
   * @return　受講生IDに紐づくコース情報
   */
  List<StudentCourse> getStudentsCourseList(int studentId);

  /**
   * 受講生を登録します。
   * @param student　追加する受講生
   */
  void addStudent(Student student);

  /**
   * 受講生のコース情報を登録します。
   *
   * @param studentCourse 　登録するコース
   */
  void addCourse(StudentCourse studentCourse);

  /**
   * 受講生情報を更新します。
   * @param student　更新する受講生
   */
  void updateStudent(Student student);

  /**
   * コース情報を更新します。
   * @param studentCourse　更新するコース
   */
  void updateCourse(StudentCourse studentCourse);

}


