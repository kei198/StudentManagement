package raisetech.student.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentsList(){
    return repository.searchStudents() ;
  }

  public List<StudentCourse> searchStudentsCoursesList(){
    return repository.searchStudentsCourses();
  }

  public List<StudentCourse> getStudentsCourse(int studentId){
    return repository.getStudentsCourses(studentId);
  }

  public Student searchStudent(int id){
    return repository.searchStudent(id);
  }

  public  int searchStudentId(){
    return repository.searchStudentId();
  }

  @Transactional
  public void addStudent(Student addStudent){
    repository.addStudent(addStudent);
  }
  @Transactional
  public void addCourse(StudentCourse studentCourse,int studentsId){
    repository.addCourse(studentCourse,studentsId);
  }

  @Transactional
  public void updateStudent(Student updateStudent){
    repository.updateStudent(updateStudent);
  }

  @Transactional
  public void updateCourse(StudentCourse studentCourse){
    repository.updateCourse(studentCourse);
  }

}
