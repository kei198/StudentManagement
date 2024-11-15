package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudents();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentsCourses();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(int id);

  @Select("SELECT MAX(id) FROM students;")
  int searchStudentId();

  @Insert("INSERT INTO students (name,ruby,mailaddress,address,age,gender,nickname,remark) "
      + "Values (#{name},#{ruby},#{mailaddress},#{address},#{age},#{gender},#{nickname},#{remark})")
  void addStudent(Student student);

  @Insert("INSERT INTO students_courses (students_id,course,start_day,completion_day) "
      + "Values(#{studentsId},#{studentCourse.course},#{studentCourse.startDay},#{studentCourse.completionDay})")
  void addCourse(StudentCourse studentCourse, int studentsId);

  @Update("UPDATE students set name = #{name},ruby=#{ruby},mailaddress=#{mailaddress},address=#{address},age=#{age},gender=#{gender},nickname=#{nickname},remark=#{remark}"
      + " WHERE id = #{id}")
  void updateStudent(Student student);
}


