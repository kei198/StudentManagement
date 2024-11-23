package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 削除フラグのたっていない受講生の全件検索を行います。
   * @return
   */
  @Select("SELECT * FROM students WHERE is_deleted = false")
  List<Student> searchStudents();

  /**
   * 受講生のコース情報の全件検索を行います。
   * @return　受講生のコース情報(全件)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentsCourses();

  /**
   * 受講生の検索を行います。
   * @param id　受講生ID
   * @return　受講生
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(int id);

  /**
   * 登録した受講生のIDを検索します。
   * データベース側でオートインクリメントで番号が発番されるため
   * 一番新しいID=登録したIDとみなしています。
   * @return　登録受講生ID
   */
  @Select("SELECT MAX(id) FROM students;")
  int searchLatestStudentId();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   * @param studentId　受講生ID
   * @return　受講生IDに紐づくコース情報
   */
  @Select("SELECT * FROM students_courses WHERE students_id = #{studentId}")
  List<StudentCourse> getStudentsCourses(int studentId);

  /**
   * 受講生を登録します。
   * @param student　追加する受講生
   */
  @Insert("INSERT INTO students (name,ruby,mailaddress,address,age,gender,nickname,remark) "
      + "Values (#{name},#{ruby},#{mailaddress},#{address},#{age},#{gender},#{nickname},#{remark})")
  void addStudent(Student student);

  /**
   * 受講生のコース情報を登録します。
   * @param studentCourse　登録するコース
   * @param studentsId　登録するコースに紐づく受講生ID
   */
  @Insert("INSERT INTO students_courses (students_id,course,start_day,completion_day) "
      + "Values(#{studentsId},#{studentCourse.course},#{studentCourse.startDay},#{studentCourse.completionDay})")
  void addCourse(StudentCourse studentCourse, int studentsId);

  /**
   * 受講生情報を更新します。
   * @param student　更新する受講生
   */
  @Update("UPDATE students set name = #{name},ruby=#{ruby},mailaddress=#{mailaddress},address=#{address},age=#{age},gender=#{gender},nickname=#{nickname},remark=#{remark},is_deleted=#{isDeleted}"
      + " WHERE id = #{id}")
  void updateStudent(Student student);

  /**
   * コース情報を更新します。
   * @param studentCourse　更新するコース
   */
  @Update("UPDATE students_courses set course=course,start_day=#{startDay},completion_day=#{completionDay}"
      + " WHERE id = #{id}")
  void updateCourse(StudentCourse studentCourse);

}


